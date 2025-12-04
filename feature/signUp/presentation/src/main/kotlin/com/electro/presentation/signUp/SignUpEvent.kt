package com.electro.presentation.signUp

import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.NewAccount

sealed interface SignUpEvent {
    data class InputEvent(val event: InputFieldEvent): SignUpEvent
    data class SignUp(val credentials: NewAccount): SignUpEvent
    data object OnNavigateToSignInScreen: SignUpEvent
    data object OpenTermsAndPrivacyPolicy: SignUpEvent
    data object CheckTermsAndPrivacy: SignUpEvent
}