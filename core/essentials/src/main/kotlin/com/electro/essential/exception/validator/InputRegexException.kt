package com.electro.essential.exception.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField

class InputRegexException(
    override val inputField: BaseInputField.TextInputField,
): BaseValidationException(
    message = "message",
) {
    override fun getLocalizedErrorMessage(stringProvider: ValidationStringProvider): String =
        stringProvider.invalidRegexInputFieldError(inputField)
}