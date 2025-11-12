package com.electro.fish.presentation

import com.electro.essential.validator.BaseInputField
import com.electro.fish.domain.entity.Credentials

sealed interface SignInEvent {
    data class SignIn(val credentials: Credentials) : SignInEvent
    data class Validate(val credentials: Credentials) : SignInEvent
    data class ClearError(val inputField: BaseInputField<*>): SignInEvent
    data class EnableErrorMessages(val inputField: BaseInputField<*>) : SignInEvent
    data object OnNavigateToSignUp: SignInEvent
    data object OnNavigateToForgotPassword: SignInEvent
}