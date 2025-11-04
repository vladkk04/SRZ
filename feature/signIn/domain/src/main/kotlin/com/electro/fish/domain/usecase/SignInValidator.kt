package com.electro.fish.domain.usecase

import com.electro.essential.validator.ValidationResult
import com.electro.essential.validator.ValidationResult.Companion.combined
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.domain.entity.toFieldValues
import jakarta.inject.Inject

class SignInValidator @Inject constructor() {
    fun validate(credentials: Credentials): ValidationResult {
        val fieldValues = credentials.toFieldValues()

        val validationResult = fieldValues
            .map { it.validate() }
            .combined()

        return validationResult
    }
}