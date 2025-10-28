package com.electro.fish.data.account.signIn.remote.dto

import com.electro.fish.data.model.Token
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponseDto(
    val token: Token
)