package com.electro.fish.domain.model

import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldValue
import com.electro.essential.validator.WithFormInputValidator

data class NewLocalAccount(
    val email: String,
    val password: String
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(DefaultAuthFormInputFieldValidation.Email, value = email),
        InputFieldValue(DefaultAuthFormInputFieldValidation.Password, value = password)
    )
}