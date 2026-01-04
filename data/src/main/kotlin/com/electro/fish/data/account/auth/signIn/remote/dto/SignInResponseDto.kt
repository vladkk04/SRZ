package com.electro.fish.data.account.auth.signIn.remote.dto

import com.electro.fish.data.account.auth.model.AuthToken
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SignInResponseDto(val token: AuthToken)