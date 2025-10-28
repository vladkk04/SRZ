package com.electro.domain.model

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.dto.Role

data class Credentials(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String,
    val role: Role,
    val address: String,
    val fishingLicenseNumber: String
)

fun Credentials.mapToCreateAccountCredentials() = CreateAccountCredentials(
    firstName = firstName,
    lastName = lastName,
    birthDate = birthDate,
    email = email,
    password = password,
    role = role,
    address = address,
    fishingLicenseNumber = fishingLicenseNumber
)