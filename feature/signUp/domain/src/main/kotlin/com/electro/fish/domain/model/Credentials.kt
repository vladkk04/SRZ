package com.electro.fish.domain.model

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.dto.Role

data class Credentials(
    val email: String,
    val password: String
)

fun Credentials.mapToNewAccount() = NewAccount(
    email = email,
    password = password
)