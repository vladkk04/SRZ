package com.electro.fish.domain.model

import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldValue
import com.electro.fish.data.account.signIn.model.AuthCredential

data class SignInCredentials(
    val email: String,
    val password: String
)

fun SignInCredentials.mapToAuthCredential() = AuthCredential(
    email = email,
    password = password
)

fun SignInCredentials.toFieldValues(): List<InputFieldValue<*>> = listOf(
    InputFieldValue(DefaultAuthFormInputFieldValidation.Email, value = email),
    InputFieldValue(DefaultAuthFormInputFieldValidation.PasswordWithoutRegex, value = password)
)