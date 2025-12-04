package com.electro.fish.domain.model

import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldValue
import com.electro.essential.validator.WithFormInputValidator
import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.dto.Role

data class NewAccount(
    val email: String,
    val password: String,
    val role: Role = Role.FISHERMAN
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(DefaultAuthFormInputFieldValidation.Email, value = email),
        InputFieldValue(DefaultAuthFormInputFieldValidation.Password, value = password)
    )
}

fun NewAccount.mapToCreateAccountCredentials() = CreateAccountCredentials(
    email = email,
    password = password,
    role = role
)
