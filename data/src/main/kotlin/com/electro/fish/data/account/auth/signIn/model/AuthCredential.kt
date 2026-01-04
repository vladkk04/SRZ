package com.electro.fish.data.account.auth.signIn.model

import com.electro.fish.data.account.auth.signIn.remote.dto.SignInRequestDto

data class AuthCredential(
    val email: String,
    val password: String
)

fun AuthCredential.mapToRequestDto() = SignInRequestDto(
    email = email,
    password = password
)