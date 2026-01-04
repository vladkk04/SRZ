package com.electro.essential.exception.validator

import com.electro.essential.exception.base.BaseValidationException
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField

class EmptyImageException(
    override val inputField: BaseInputField.ImageInputField<*>,
): BaseValidationException(
    message = "Image field is empty"
) {
    override fun getLocalizedErrorMessage(stringProvider: ValidationStringProvider): String {
        return stringProvider.emptyImageFieldError(inputField)
    }
}