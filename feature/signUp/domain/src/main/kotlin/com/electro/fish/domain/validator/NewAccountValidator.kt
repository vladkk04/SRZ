package com.electro.domain.validator

import com.electro.domain.model.NewAccount
import com.electro.essential.validator.ValidationResult

interface NewAccountValidator {
    suspend fun validate(account: NewAccount): ValidationResult
}