package com.electro.fish.presentation.signUp

import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.NewLocalAccount

sealed interface SignUpEvent {
    data class InputEvent(val event: InputFieldEvent): SignUpEvent
    data class SignUp(val credentials: NewLocalAccount): SignUpEvent
    data object OnNavigateToSignInScreen: SignUpEvent
    data object OpenTermsAndPrivacyPolicy: SignUpEvent
    data object CheckTermsAndPrivacy: SignUpEvent
}