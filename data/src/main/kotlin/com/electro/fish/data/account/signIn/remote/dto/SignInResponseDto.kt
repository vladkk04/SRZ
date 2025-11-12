package com.electro.fish.data.account.signIn.remote.dto

import com.electro.fish.data.model.AuthToken
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val authToken: AuthToken
)