package com.electro.fish.data.account.auth.signUp.remote.dto

import com.electro.fish.data.account.auth.model.Role
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val role: Role,
    val address: String,
    val licenseExpiryDate: String
)