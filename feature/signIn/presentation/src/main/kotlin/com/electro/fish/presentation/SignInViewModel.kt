@file:OptIn(FlowPreview::class)

package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.data.account.auth.model.Role
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.fish.domain.usecase.SignInUseCase
import com.electro.fish.presentation.navigation.SignInNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUseCase: SignInUseCase,
    private val navigator: SignInNavigator,
    private val stringProvider: SignInStringProvider,
    private val toastExceptionHandler: ToastExceptionHandler
) : ViewModel() {

    private val _state = MutableStateFlow(
        SignInStateImpl(stringProvider = stringProvider, inputFormState = InputFormState(stringProvider))
    )

    val state: StateFlow<SignInState> = _state.asStateFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SignIn -> signIn(event.credentials)
            is SignInEvent.InputEvent -> updateFormState(event.event)
            SignInEvent.OnNavigateToForgotPassword -> navigator.launchForgotPasswordScreen()
            SignInEvent.OnNavigateToSignUp -> navigator.launchSignUpScreen()
        }
    }

    private fun updateFormState(event: InputFieldEvent) {
        _state.update { currentState ->
            currentState.copy(inputFormState = currentState.inputFormState.onEvent(event))
        }
    }

    private fun signIn(credentials: SignInCredentials) = viewModelScope.launch {
        try {
            _state.update { it.copy(isSignInInProgress = true) }
            val role = signUseCase.invoke(credentials)
            when (role) {
                Role.FISHERMAN, Role.GUEST -> {
                    navigator.launchHomeScreen()
                }
                Role.CONTROLLER -> {
                    navigator.launchInspectorScreen()
                }
            }
        } catch (e: BaseValidationException) {
            _state.update { it.copy(inputFormState = it.inputFormState.withValidationException(e)) }
        } catch (e: BaseAppException) {
            toastExceptionHandler.handleException(e)
        } finally {
            _state.update { it.copy(isSignInInProgress = false) }
        }
    }

    private data class SignInStateImpl(
        override val isSignInInProgress: Boolean = false,
        override val stringProvider: SignInStringProvider,
        override val inputFormState: InputFormState = InputFormState(stringProvider)
    ) : SignInState
}


