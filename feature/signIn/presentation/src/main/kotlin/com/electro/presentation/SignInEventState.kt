package com.electro.presentation

sealed class SignInUiEvent() {
    data class EmailChanged(val email: String) : SignInUiEvent()
    data class PasswordChanged(val password: String) : SignInUiEvent()
    data object SignIn : SignInUiEvent()
}