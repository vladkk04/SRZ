package com.electro.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.domain.entity.Credentials
import com.electro.domain.entity.InputField
import com.electro.domain.exceptions.EmptyInputFieldException
import com.electro.domain.resources.SignInStringProvider
import com.electro.domain.usecase.SignInUseCase
import com.electro.essential.handler.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signUseCase: SignInUseCase,
    private val exceptionHandler: ExceptionHandler,
    private val signInStringProvider: SignInStringProvider
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state get() = _state.asStateFlow()

    fun signIn(credentials: Credentials) = viewModelScope.launch {
       try {
           showProgressIndicator()
           signUseCase.invoke(credentials)
           hideProgressIndicator()
       } catch (e: EmptyInputFieldException) {
           ensureActive()
           showFieldErrorMessage(e.inputField)
           hideProgressIndicator()
       } catch (e: Exception) {
           e.printStackTrace()
           ensureActive()
           exceptionHandler.handleException(e)
           hideProgressIndicator()
           Log.d("debug", e.toString())
       }
    }

    private fun showFieldErrorMessage(field: InputField) {
        when (field) {
            InputField.Email -> {
                _state.update {
                   it.copy(
                       emailInputErrorMessage = signInStringProvider.invalidEmailError,
                   )
                }
            }

            InputField.Password -> {
                _state.update {
                    it.copy(
                        passwordInputErrorMessage = signInStringProvider.invalidEmailError,
                    )
                }
            }
        }
        hideProgressIndicator()
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


