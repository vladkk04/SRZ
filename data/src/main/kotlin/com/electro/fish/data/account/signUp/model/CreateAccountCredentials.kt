package com.electro.fish.data.account.signUp.model

import com.electro.fish.data.account.signUp.remote.dto.Role

data class CreateAccountCredentials(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String,
    val role: Role,
    val address: String,
    val fishingLicenseNumber: String
)
