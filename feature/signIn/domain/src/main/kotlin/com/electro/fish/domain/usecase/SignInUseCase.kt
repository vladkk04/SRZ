package com.electro.fish.domain.usecase

import com.electro.essential.validator.ValidationResult
import com.electro.fish.data.AccountRepository
import com.electro.fish.data.LocalTokenRepository
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.domain.entity.mapToAuthCredential
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val localTokenRepository: LocalTokenRepository,
    private val signInValidator: SignInValidator,
) {
    suspend operator fun invoke(credential: Credentials) {
        when (val result = signInValidator.validate(credential)) {
            is ValidationResult.Failure -> throw result.exceptions.first()
            ValidationResult.Success -> {
                val token = accountRepository.signIn(credential.mapToAuthCredential())
                localTokenRepository.saveToken(token)
            }
        }
    }
}

