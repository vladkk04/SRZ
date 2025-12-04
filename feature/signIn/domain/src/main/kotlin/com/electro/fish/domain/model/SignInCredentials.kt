package com.electro.fish.domain.model

import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.WithFormInputValidator
import com.electro.essential.validator.InputFieldValue
import com.electro.fish.data.account.signIn.model.AuthCredential

data class SignInCredentials(
    val email: String,
    val password: String
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(DefaultAuthFormInputFieldValidation.Email, value = email),
        InputFieldValue(DefaultAuthFormInputFieldValidation.PasswordWithoutRegex, value = password)
    )
}

fun SignInCredentials.mapToAuthCredential() = AuthCredential(
    email = email,
    password = password
)