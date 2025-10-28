package com.electro.domain.entity

import com.electro.fish.data.account.signIn.model.AuthCredential

data class Credentials(
    val email: String,
    val password: String
)

fun Credentials.mapToAuthCredential() = AuthCredential(
    email = email,
    password = password
)