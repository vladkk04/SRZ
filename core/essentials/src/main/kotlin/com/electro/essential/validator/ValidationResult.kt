package com.electro.essential.validator

import com.electro.essential.exception.base.BaseValidationException

sealed interface ValidationResult {

    operator fun plus(exception: BaseValidationException): ValidationResult

    data object Success : ValidationResult {
        override fun plus(exception: BaseValidationException): ValidationResult {
            return Failure(exception)
        }
    }

    data class Failure(
        val exceptions: List<BaseValidationException>
    ) : ValidationResult {
        constructor(exception: BaseValidationException) : this(listOf(exception))

        override fun plus(exception: BaseValidationException): ValidationResult {
            return Failure(exceptions + exception)
        }
    }

    companion object {
        fun List<ValidationResult>.combined() : ValidationResult {
            val exceptions = this.filterIsInstance<Failure>()
                .flatMap { it.exceptions }

            return if (exceptions.isEmpty()) { Success } else { Failure(exceptions) }
        }
    }
}