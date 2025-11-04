package com.electro.essential.resources

import com.electro.essential.validator.BaseInputField

interface ValidationStringProvider: StringProvider {
    val email: String
    val password: String

    val passwordRegexError: String

    fun emptyInputFieldError(field: BaseInputField.TextInputField): String
    fun invalidRegexInputFieldError(field: BaseInputField.TextInputField): String
}