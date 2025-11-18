@file:OptIn(FlowPreview::class)

package com.electro.fish.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.exception.NetworkException
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.exception.validator.EmptyInputFieldException
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.essential.validator.ValidationResult
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.fish.domain.usecase.SignInUseCase
import com.electro.fish.domain.usecase.SignInValidator
import com.electro.fish.presentation.navigation.SignInNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUseCase: SignInUseCase,
    private val signInValidator: SignInValidator,
    private val signInNavigator: SignInNavigator,
    private val stringProvider: ValidationStringProvider,
    private val toastExceptionHandler: ToastExceptionHandler
) : ViewModel() {

    private val _state: MutableStateFlow<SignInStateImpl> = MutableStateFlow(SignInStateImpl())
    val state: StateFlow<SignInState> get() = _state.asStateFlow()

    private val validateRequestFlow = MutableSharedFlow<SignInCredentials>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private fun launchHomeScreen() = signInNavigator.launchHomeScreen()

    private fun launchSignUpScreen() = signInNavigator.launchSignUpScreen()

    private fun launchForgotPasswordScreen() = signInNavigator.launchForgotPasswordScreen()

    init {
        viewModelScope.launch { validateRequestFlow.debounce(1000).collect(::validate) }
    }

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignIn -> signIn(event.credentials)
            is SignInEvent.Validate -> validateRequestFlow.tryEmit(event.credentials)
            is SignInEvent.ClearError -> clearError(event.inputField)
            is SignInEvent.EnableErrorMessages -> enableErrorMessages(event.inputField)
            SignInEvent.OnNavigateToForgotPassword -> launchForgotPasswordScreen()
            SignInEvent.OnNavigateToSignUp -> launchSignUpScreen()
        }
    }

    private fun signIn(credentials: SignInCredentials) = viewModelScope.launch {
        try {
            //showProgressIndicator()
            signUseCase.invoke(credentials)
            launchHomeScreen()
        } catch (e: Exception) {
            toastExceptionHandler.handleException(e)
        }
    }

    private fun validate(credentials: SignInCredentials) = viewModelScope.launch {
        val validationResult = signInValidator.validate(credentials)
        _state.update { it.withNewValidationResult(validationResult) }
    }

    private fun clearError(inputField: BaseInputField<*>) =
        _state.update { it.clearError(inputField) }

    private fun enableErrorMessages(inputField: BaseInputField<*>) =
        _state.update { it.enableErrorMessages(inputField) }


    private data class SignInStateImpl(
        override val isSignInInProgress: Boolean = false,
        val allErrorMessages: Map<BaseInputField<*>, String> = emptyMap(),
        val fieldsWithEnabledErrors: Set<BaseInputField<*>> = emptySet()
    ) : SignInState {
        override val errorMessages: ImmutableMap<BaseInputField<*>, String> =
            allErrorMessages.filterKeys(fieldsWithEnabledErrors::contains).toImmutableMap()
    }

    private fun SignInStateImpl.withValidationException(e: BaseValidationException) = copy(
        allErrorMessages = (allErrorMessages + toErrorMessagePair(e)).toImmutableMap(),
    )

    private fun SignInStateImpl.withNewValidationResult(validationResult: ValidationResult) = copy(
        allErrorMessages = validationResult.toErrorMessagesMap()
    )

    private fun SignInStateImpl.clearError(inputField: BaseInputField<*>) = copy(
        allErrorMessages = allErrorMessages.filterKeys { it != inputField }
    )

    private fun SignInStateImpl.enableErrorMessages(inputField: BaseInputField<*>) = copy(
        fieldsWithEnabledErrors = fieldsWithEnabledErrors + inputField
    )

    private fun ValidationResult.toErrorMessagesMap(): Map<BaseInputField<*>, String> {
        return when (this) {
            is ValidationResult.Error -> exceptions.associate(::toErrorMessagePair)
            is ValidationResult.Success -> emptyMap()
        }
    }

    private fun handleValidationException(e: BaseValidationException) =
        _state.update { it.withValidationException(e) }

    private fun toErrorMessagePair(e: BaseValidationException) =
        e.inputField to e.getLocalizedErrorMessage(stringProvider)
}


