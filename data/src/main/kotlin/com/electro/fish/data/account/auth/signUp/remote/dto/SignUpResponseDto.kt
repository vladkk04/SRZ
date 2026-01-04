package com.electro.fish.data.account.auth.signUp.remote.dto

import com.electro.fish.data.account.auth.model.AuthToken
import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SignUpResponseDto(val token: AuthToken)