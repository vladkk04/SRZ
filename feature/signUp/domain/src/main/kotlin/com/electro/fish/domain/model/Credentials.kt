package com.electro.fish.domain.model

data class Credentials(
    val email: String,
    val password: String
)

fun Credentials.mapToNewAccount() = NewAccount(
    email = email,
    password = password
)