package com.electro.presentation.signUp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.BrowserCustomTab
import com.electro.fish.domain.model.NewAccount
import com.electro.fish.domain.resources.SignUpStringProvider
import com.electro.essential.ToastExceptionHandler
import com.electro.fish.domain.usecase.SignUpUseCase
import com.electro.presentation.signUp.navigation.SignUpNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val toastExceptionHandler: ToastExceptionHandler,
    private val signUpStringProvider: SignUpStringProvider,
    private val signUpNavigator: SignUpNavigator,
    private val browserCustomTab: BrowserCustomTab
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state get() = _state.asStateFlow()

    fun launchSignInScreen() {
        signUpNavigator.launchSignInScreen()
    }

    fun onEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.OnNavigateToSignInScreen -> {

            }
            SignUpEvent.OpenTermsAndPrivacyPolicy -> {

            }
            is SignUpEvent.SignUp -> {

            }
        }
    }

    fun signUp(newAccount: NewAccount) = viewModelScope.launch {
       try {
           showProgressIndicator()
           signUpUseCase.invoke(newAccount)
           hideProgressIndicator()
       } catch (e: Exception) {
           e.printStackTrace()
           ensureActive()
           hideProgressIndicator()
           Log.d("debug", e.toString())
       }
    }

    private fun showFieldErrorMessage() {
       /* when (field) {
            InputField.Email -> {
                _state.update {
                   it.copy(
                       emailInputErrorMessage = signUpStringProvider.invalidEmailError,
                   )
                }
            }

            InputField.Password -> {
                _state.update {
                    it.copy(
                        passwordInputErrorMessage = signUpStringProvider.invalidEmailError,
                    )
                }
            }
        }
        hideProgressIndicator()*/
    }

    private fun showProgressIndicator() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private fun hideProgressIndicator() {
        _state.update {
            it.copy(
                isLoading = false
            )
        }
    }

    fun onClearInputErrorMessage() {
        _state.update {
            it.copy(
                emailInputErrorMessage = null,
                passwordInputErrorMessage = null
            )
        }
    }

    fun open() {
        browserCustomTab.openCustomTab("https://developer.chrome.com/docs/android/custom-tabs/guide-get-started")
    }
}


