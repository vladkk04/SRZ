package com.electro.fish.data.account.signUp.model

import com.electro.fish.data.account.signUp.remote.dto.Role

data class CreateAccountCredentials(
    val email: String,
    val password: String,
    val role: Role
)
