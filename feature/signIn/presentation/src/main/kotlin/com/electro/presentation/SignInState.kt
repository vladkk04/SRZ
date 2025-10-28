package com.electro.presentation

import com.electro.domain.entity.InputField

data class SignInState(
    val emailInputErrorMessage: String? = null,
    val passwordInputErrorMessage: String? = null,
    val isLoading: Boolean = false,
)