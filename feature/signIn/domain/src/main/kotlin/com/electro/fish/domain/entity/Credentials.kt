package com.electro.fish.domain.entity

import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldValue
import com.electro.fish.data.account.signIn.model.AuthCredential

data class Credentials(
    val email: String,
    val password: String
)

fun Credentials.mapToAuthCredential() = AuthCredential(
    email = email,
    password = password
)

fun Credentials.toFieldValues(): List<InputFieldValue<*>> = listOf(
    InputFieldValue(DefaultAuthFormInputFieldValidation.Email, value = email),
    InputFieldValue(DefaultAuthFormInputFieldValidation.Password, value = password)
)