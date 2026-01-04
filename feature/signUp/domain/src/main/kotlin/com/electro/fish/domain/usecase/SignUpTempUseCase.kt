package com.electro.fish.domain.usecase

import com.electro.essential.validator.ValidationResult
import com.electro.essential.validator.validate
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataSaver
import com.electro.fish.domain.model.NewLocalAccount
import javax.inject.Inject

class SignUpTempUseCase @Inject constructor(
    private val localAccountTempDataSaver: LocalAccountTempDataSaver
) {
    suspend operator fun invoke(localAccount: NewLocalAccount) {
        when (val result = localAccount.validate()) {
            is ValidationResult.Error -> throw result.exceptions.first()
            ValidationResult.Success -> {
                localAccountTempDataSaver.saveEmail(localAccount.email)
                localAccountTempDataSaver.savePassword(localAccount.password)
            }
        }
    }
}