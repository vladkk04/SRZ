package com.electro.fish.data.account.auth.signIn.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestDto(
    val email: String,
    val password: String
)