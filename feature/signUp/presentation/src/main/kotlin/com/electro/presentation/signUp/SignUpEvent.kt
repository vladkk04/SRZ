package com.electro.presentation.signUp

import com.electro.fish.domain.model.Credentials

sealed interface SignUpEvent {
    data class SignUp(val credentials: Credentials): SignUpEvent
    data object OnNavigateToSignInScreen: SignUpEvent
    data object OpenTermsAndPrivacyPolicy: SignUpEvent
}