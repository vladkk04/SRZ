package com.electro.essential.resources

import com.electro.essential.validator.BaseInputField

interface ValidationStringProvider: StringProvider {
    fun emptyInputFieldError(field: BaseInputField.TextInputField): String
    fun invalidRegexInputFieldError(field: BaseInputField.TextInputField): String
}

