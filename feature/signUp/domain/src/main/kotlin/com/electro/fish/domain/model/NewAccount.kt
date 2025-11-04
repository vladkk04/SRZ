package com.electro.domain.model

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.dto.Role

data class NewAccount(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val repeatPassword: String,
    val role: Role,
    val address: String,
    val birthDate: String,
    val fishingLicenseNumber: String
)

fun NewAccount.mapToCreateAccountCredentials() = CreateAccountCredentials(
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    role = role,
    address = address,
    birthDate = birthDate,
    fishingLicenseNumber = fishingLicenseNumber
)
