package com.electro.fish.presentation

data class SignInState(
    val emailInputFieldErrorMessage: String? = null,
    val passwordInputFieldErrorMessage: String? = null,
    val isLoading: Boolean = false,
)