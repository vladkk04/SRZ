package com.electro.fish.presentation.model

import androidx.compose.runtime.Immutable
import com.electro.fish.data.profile.model.ProfileData

@Immutable
data class ProfileDataUi(
    val firstName: String,
    val lastName: String,
)

fun ProfileData.mapToProfileDataUi() = ProfileDataUi(
    firstName = firstName,
    lastName = lastName
)