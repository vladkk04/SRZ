package com.electro.fish.presentation

import androidx.compose.runtime.Immutable
import com.electro.fish.presentation.model.ProfileDataUi

@Immutable
interface ProfileState {
    val profileDataUi: ProfileDataUi
}