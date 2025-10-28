package com.electro.presentation.signUp

data class SignUpInState(
    val emailInputErrorMessage: String? = null,
    val passwordInputErrorMessage: String? = null,
    val isLoading: Boolean = false,
)