package com.electro.essential.validator

import com.electro.essential.exception.validator.EmptyInputFieldException
import com.electro.essential.exception.validator.InputRegexException
import com.electro.essential.resources.ValidationStringProvider

sealed interface BaseInputField<T : Any> {

    val fieldName: ValidationStringProvider.() -> String

    fun validate(value: T): ValidationResult

    sealed class TextInputField(
        override val fieldName: ValidationStringProvider.() -> String,
        val regexMessage: (ValidationStringProvider.() -> String)? = null,
        val regex: Regex? = null,
    ) : BaseInputField<String> {
        override fun validate(value: String): ValidationResult {
            val trimmedValue = value.trim()
            return when {
                trimmedValue.isEmpty() -> ValidationResult.Failure(EmptyInputFieldException(this))
                regex != null && !regex.matches(trimmedValue) -> ValidationResult.Failure(InputRegexException(this))
                else -> ValidationResult.Success
            }
        }
    }

    data object Email : TextInputField(
        fieldName = { this.email },
        regex = InputFieldRegex.emailRegex
    )

    data object Password: TextInputField(
        fieldName = { this.email },
        regexMessage = { this.passwordRegexError },
        regex = InputFieldRegex.passwordRegex
    )
}

