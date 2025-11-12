package com.electro.fish.data.account.signUp.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    val email: String,
    val password: String,
    val role: Role
)

@Serializable
enum class Role {
    ADMIN,
    FISHERMAN
}

