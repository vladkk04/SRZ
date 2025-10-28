package com.electro.domain.usecase

import com.electro.domain.model.NewAccount
import com.electro.domain.validator.NewAccountValidator
import com.electro.essential.validator.ValidationResult
import javax.inject.Inject

class ValidateAccountUseCase @Inject constructor(
    private val newAccountValidator: NewAccountValidator
) {

    suspend operator fun invoke(newAccount: NewAccount): ValidationResult {
        return newAccountValidator.validate(newAccount)
    }

}