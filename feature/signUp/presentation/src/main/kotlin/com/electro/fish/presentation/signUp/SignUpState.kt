package com.electro.fish.presentation.signUp

import androidx.compose.runtime.Immutable
import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.resources.SignUpStringProvider

@Immutable
interface SignUpState {
    val isSignUpInProgress: Boolean
    val isTermsAndPrivacyChecked: Boolean
    val stringProvider: SignUpStringProvider
    val inputFormState: InputFormState
}