package com.electro.presentation.signUp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.domain.model.NewAccount
import com.electro.domain.resources.SignUpStringProvider
import com.electro.domain.usecase.SignUpUseCase
import com.electro.essential.handler.ExceptionHandler
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
    private val exceptionHandler: ExceptionHandler,
    private val signUpStringProvider: SignUpStringProvider
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpInState())
    val state get() = _state.asStateFlow()

    fun signUp(newAccount: NewAccount) = viewModelScope.launch {
       try {
           showProgressIndicator()
           signUpUseCase.invoke(newAccount)
           hideProgressIndicator()
       } catch (e: Exception) {
           e.printStackTrace()
           ensureActive()
           exceptionHandler.handleException(e)
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
}


