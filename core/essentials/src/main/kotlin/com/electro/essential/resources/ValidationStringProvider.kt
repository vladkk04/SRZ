package com.electro.essential.resources

import com.electro.essential.validator.BaseInputField

interface ValidationStringProvider: StringProvider {
    fun emptyInputFieldError(field: BaseInputField.InputField): String

    fun emptyImageFieldError(field: BaseInputField.ImageInputField<*>): String = "Image field is empty"

    fun invalidRegexInputFieldError(
        field: BaseInputField.InputField,
        customErrorMessageResolver: ((ValidationStringProvider) -> String)?
    ): String
}

