package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.exception.validator.EmptyInputFieldException
import com.electro.essential.exception.validator.InputRegexException
import com.electro.essential.manager.ToastManager
import com.electro.essential.resources.ValidationStringProvider
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.domain.usecase.SignInUseCase
import com.electro.fish.presentation.navigation.SignInNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUseCase: SignInUseCase,
    private val toastManager: ToastManager,
    private val signInNavigator: SignInNavigator,
    private val validationStringProvider: ValidationStringProvider
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state get() = _state.asStateFlow()

    fun launchSignInScreen() {
        signInNavigator.launchSignUpScreen()
    }

    fun signIn(credentials: Credentials) = viewModelScope.launch {
        try {
            showProgressIndicator()
            signUseCase.invoke(credentials)
            hideProgressIndicator()
        } catch (e: BaseValidationException) {
            when (e) {
                is EmptyInputFieldException, is InputRegexException -> {

                }
            }
            hideProgressIndicator()
        }
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
                emailInputFieldErrorMessage = null,
                passwordInputFieldErrorMessage = null
            )
        }
    }
}


