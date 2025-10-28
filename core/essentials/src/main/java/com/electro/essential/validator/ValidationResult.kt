package com.electro.essential.validator

import com.electro.essential.exception.base.BaseValidationException

sealed interface ValidationResult {

    data object Success : ValidationResult

    data class Failure(
        val exceptions: List<BaseValidationException>
    ) : ValidationResult

}