package com.electro.fish.data.account.signUp.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val fistName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String,
    val role: Role,
    val address: String,
    val fishingLicenseNumber: String
)

@Serializable
enum class Role {
    ADMIN,
    FISHERMAN
}

