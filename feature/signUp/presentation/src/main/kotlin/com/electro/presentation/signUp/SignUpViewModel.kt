package com.electro.presentation.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.BrowserCustomTab
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.model.NewAccount
import com.electro.fish.domain.resources.SignUpStringProvider
import com.electro.fish.domain.usecase.SignUpUseCase
import com.electro.presentation.signUp.navigation.SignUpNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val navigator: SignUpNavigator,
    private val stringProvider: SignUpStringProvider,
    private val browserCustomTab: BrowserCustomTab,
    private val toastExceptionHandler: ToastExceptionHandler
) : ViewModel() {

    private val _state = MutableStateFlow(
        SignUpStateImpl(
            stringProvider = stringProvider,
            inputFormState = InputFormState(stringProvider)
        )
    )

    val state: StateFlow<SignUpState> = _state.asStateFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.InputEvent -> updateFormState(event.event)
            is SignUpEvent.SignUp -> signUp(event.credentials)
            SignUpEvent.OnNavigateToSignInScreen -> navigator.launchSignInScreen()
            SignUpEvent.OpenTermsAndPrivacyPolicy -> open()
            SignUpEvent.CheckTermsAndPrivacy -> isTermsAndPrivacyChecked()
        }
    }

    fun signUp(newAccount: NewAccount) = viewModelScope.launch {
        try {
            _state.update { it.copy(isSignUpInProgress = true) }
            signUpUseCase.invoke(newAccount)
            navigator.launchProfileSetupScreen()
        } catch (e: BaseValidationException) {
            _state.update {
                it.copy(
                    isSignUpInProgress = false,
                    inputFormState = it.inputFormState.withValidationException(e)
                )
            }
        } catch (e: BaseAppException) {
            _state.update { it.copy(isSignUpInProgress = false) }
            toastExceptionHandler.handleException(e)
        }
    }

    private fun isTermsAndPrivacyChecked() {
        _state.update { it.copy(isTermsAndPrivacyChecked = !it.isTermsAndPrivacyChecked) }
    }

    private fun updateFormState(event: InputFieldEvent) {
        _state.update { currentState ->
            currentState.copy(inputFormState = currentState.inputFormState.onEvent(event))
        }
    }

    private fun open() {
        browserCustomTab.openCustomTab("https://developer.chrome.com/docs/android/custom-tabs/guide-get-started")
    }

    private data class SignUpStateImpl(
        override val isSignUpInProgress: Boolean = false,
        override val isTermsAndPrivacyChecked: Boolean = false,
        override val stringProvider: SignUpStringProvider,
        override val inputFormState: InputFormState = InputFormState(stringProvider)
    ) : SignUpState
}


