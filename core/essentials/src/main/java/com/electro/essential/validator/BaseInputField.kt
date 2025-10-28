package com.electro.essential.validator

import com.electro.essential.resources.StringProvider

interface BaseInputField <T: Any> {
    val label: String
    val isRequired: Boolean
    fun validate(value: T): ValidationResult
}

interface TextInputField: BaseInputField<String> {
    val maxLength: Int?
    val minLength: Int?
    val regex: Regex?
}
