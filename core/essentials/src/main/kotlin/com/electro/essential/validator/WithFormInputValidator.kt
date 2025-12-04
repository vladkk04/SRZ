package com.electro.essential.validator

import com.electro.essential.validator.ValidationResult.Companion.combined

interface WithFormInputValidator {
    fun toFieldValues(): List<InputFieldValue<*>>

    fun validate(): ValidationResult {
        val fieldValues = toFieldValues()

        val validationResult = fieldValues
            .map { it.validate() }
            .combined()

        return validationResult
    }
}