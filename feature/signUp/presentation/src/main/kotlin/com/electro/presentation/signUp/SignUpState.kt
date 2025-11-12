package com.electro.presentation.signUp

data class SignUpState(
    val emailInputErrorMessage: String? = null,
    val passwordInputErrorMessage: String? = null,
    val isLoading: Boolean = false,
)