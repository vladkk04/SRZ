package com.electro.essential.exception.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField

class EmptyInputFieldException(
    override val inputField: BaseInputField.TextInputField,
): BaseValidationException(
    message = "$inputField is empty"
) {
    override fun getLocalizedErrorMessage(stringProvider: ValidationStringProvider): String {
        return stringProvider.emptyInputFieldError(inputField)
    }

}