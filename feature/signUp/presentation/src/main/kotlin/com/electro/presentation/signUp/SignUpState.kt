package com.electro.presentation.signUp

import androidx.compose.runtime.Immutable
import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.resources.SignUpStringProvider

@Immutable
interface SignUpState {
    val isSignUpInProgress: Boolean
    val isTermsAndPrivacyChecked: Boolean
    val inputFormState: InputFormState
    val stringProvider: SignUpStringProvider
}