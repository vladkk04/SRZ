package com.electro.fish.presentation

import androidx.compose.runtime.Immutable
import com.electro.essential.validator.BaseInputField
import kotlinx.collections.immutable.ImmutableMap

@Immutable
interface SignInState {
    val isSignInInProgress: Boolean
    val errorMessages: ImmutableMap<BaseInputField<*>, String>
}