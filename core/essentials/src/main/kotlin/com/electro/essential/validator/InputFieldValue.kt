package com.electro.essential.validator

data class InputFieldValue<T: Any>(
    val inputField: BaseInputField<T>,
    val value: T
) {
    fun validate(): ValidationResult = inputField.validate(value)
}
