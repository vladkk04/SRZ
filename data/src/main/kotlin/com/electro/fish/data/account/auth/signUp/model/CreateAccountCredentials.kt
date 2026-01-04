package com.electro.fish.data.account.auth.signUp.model

import com.electro.fish.data.account.auth.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.account.auth.model.Role

data class CreateAccountCredentials(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthday: String,
    val password: String,
    val email: String,
    val role: Role,
    val address: String,
    val licenseExpiryDate: String
)

fun CreateAccountCredentials.mapToRequestDto() = SignUpRequestDto(
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    birthDate = birthday,
    password = password,
    email = email,
    role = role,
    address = address,
    licenseExpiryDate = licenseExpiryDate
)
