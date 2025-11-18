package com.electro.fish.domain.usecase

import com.electro.essential.validator.ValidationResult
import com.electro.essential.validator.ValidationResult.Companion.combined
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.model.toFieldValues
import jakarta.inject.Inject

class SignInValidator @Inject constructor() {
    fun validate(credentials: SignInCredentials): ValidationResult {
        val fieldValues = credentials.toFieldValues()

        val validationResult = fieldValues
            .map { it.validate() }
            .combined()

        return validationResult
    }
}