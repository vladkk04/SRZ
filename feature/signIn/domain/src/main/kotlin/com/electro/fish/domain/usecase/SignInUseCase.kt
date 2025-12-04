package com.electro.fish.domain.usecase

import com.electro.essential.exception.HttpException
import com.electro.essential.validator.ValidationResult
import com.electro.fish.data.AccountRepository
import com.electro.fish.data.LocalAuthTokenRepository
import com.electro.fish.domain.exceptions.EmailNotFoundInSystemException
import com.electro.fish.domain.exceptions.InvalidCredentialsException
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.model.mapToAuthCredential
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val localAuthTokenRepository: LocalAuthTokenRepository,
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(credential: SignInCredentials) {
        when (val result = credential.validate()) {
            is ValidationResult.Error -> throw result.exceptions.first()
            ValidationResult.Success -> {
                try {
                    val token = accountRepository.signIn(credential.mapToAuthCredential())
                    localAuthTokenRepository.saveToken(token.token)
                } catch (e: HttpException) {
                    when (e.code) {
                        401 -> throw InvalidCredentialsException()
                        404 -> throw EmailNotFoundInSystemException()
                        else -> throw e
                    }
                }
            }
        }
    }
}

