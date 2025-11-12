package com.electro.fish.presentation

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.base.Container
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.essential.validator.ValidationResult
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.fish.domain.usecase.SignInUseCase
import com.electro.fish.domain.usecase.SignInValidator
import com.electro.fish.presentation.navigation.SignInNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUseCase: SignInUseCase,
    private val signInNavigator: SignInNavigator,
    private val toastExceptionHandler: ToastExceptionHandler,
    private val stringProvider: SignInStringProvider,
    private val signInValidator: SignInValidator
) : ViewModel() {
    private val _state: MutableStateFlow<Container<SignInStateImpl>> = MutableStateFlow(Container.Success(SignInStateImpl()))
    val state: StateFlow<Container<SignInState>> get() = _state.asStateFlow()

    private fun launchHomeScreen()= signInNavigator.launchHomeScreen()

    private fun launchSignUpScreen() = signInNavigator.launchSignUpScreen()

    private fun launchForgotPasswordScreen() = signInNavigator.launchForgotPasswordScreen()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignIn -> signIn(event.credentials)
            is SignInEvent.Validate -> validate(event.credentials)
            is SignInEvent.ClearError -> clearError(event.inputField)
            is SignInEvent.EnableErrorMessages -> enableErrorMessages(event.inputField)
            SignInEvent.OnNavigateToForgotPassword -> launchForgotPasswordScreen()
            SignInEvent.OnNavigateToSignUp -> launchSignUpScreen()
        }
    }

    private fun clearError(inputField: BaseInputField<*>) {

    }


    private fun enableErrorMessages(inputField: BaseInputField<*>) {

    }

    private fun validate(credentials: Credentials) = viewModelScope.launch {
        try {
            val validationResult = signInValidator.validate(credentials)

            /*_state.update {
                val errorMap: Map<BaseInputField<*>, String> = when (validationResult) {
                    is ValidationResult.Error -> validationResult.exceptions.associate()
                    is ValidationResult.Success -> emptyMap()
                }



            }*/

        } catch (e: Exception) {
            ensureActive()
            Log.d("debug", e.message.toString())
        }
    }

    private fun BaseValidationException.toErrorMessagePair() =
        inputField to getLocalizedErrorMessage(stringProvider)

    private fun SignInStateImpl.withValidationException(e: BaseValidationException) = copy(
        allErrorMessages = (allErrorMessages + (e.inputField to e.message)).toImmutableMap(),
    )


    private fun signIn(credentials: Credentials) = viewModelScope.launch {
        try {
            //showProgressIndicator()
            signUseCase.invoke(credentials)
            launchHomeScreen()
        } catch (e: BaseAppException) {
            toastExceptionHandler.showExceptionToast(e)
        } catch (e: BaseValidationException) {
            ensureActive()
            handleValidationException(e)
        } finally {
            //hideProgressIndicator()
        }
    }

    private fun handleValidationException(e: BaseValidationException) {
       /* _state.update { state ->

        }*/
    }


    @Stable
    interface SignInState {
        val isSignInInProgress: Boolean
        val errorMessages: ImmutableMap<BaseInputField<*>, String>
    }

    private data class SignInStateImpl(
        override val isSignInInProgress: Boolean = false,
        val allErrorMessages: Map<BaseInputField<*>, String> = emptyMap(),
        val fieldsWithEnabledErrors: Set<BaseInputField<*>> = emptySet()
    ) : SignInState {
        override val errorMessages: ImmutableMap<BaseInputField<*>, String> =
            allErrorMessages.filterKeys(fieldsWithEnabledErrors::contains).toImmutableMap()
    }
}


