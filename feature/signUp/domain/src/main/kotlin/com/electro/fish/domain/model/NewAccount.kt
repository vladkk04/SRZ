package com.electro.fish.domain.model

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.dto.Role

data class NewAccount(
    val email: String,
    val password: String,
    val role: Role = Role.FISHERMAN
)

fun NewAccount.mapToCreateAccountCredentials() = CreateAccountCredentials(
    email = email,
    password = password,
    role = role
)
