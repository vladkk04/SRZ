package com.electro.fish.presentation

import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.SignInCredentials

sealed interface SignInEvent {
    data class InputEvent(val event: InputFieldEvent) : SignInEvent
    data class SignIn(val credentials: SignInCredentials) : SignInEvent
    data object OnNavigateToSignUp: SignInEvent
    data object OnNavigateToForgotPassword: SignInEvent
}
