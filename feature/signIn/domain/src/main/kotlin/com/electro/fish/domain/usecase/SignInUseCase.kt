package com.electro.fish.domain.usecase

import com.electro.essential.exception.HttpException
import com.electro.essential.validator.ValidationResult
import com.electro.essential.validator.validate
import com.electro.fish.data.account.auth.signIn.AccountRepository
import com.electro.fish.data.account.auth.model.Role
import com.electro.fish.data.account.auth.model.getRole
import com.electro.fish.data.account.auth.token.LocalAuthTokenRepository
import com.electro.fish.domain.exceptions.EmailNotFoundInSystemException
import com.electro.fish.domain.exceptions.InvalidCredentialsException
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.model.mapToAuthCredential
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val localAuthTokenRepository: LocalAuthTokenRepository,
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(credential: SignInCredentials): Role {
        when (val result = credential.validate()) {
            is ValidationResult.Error -> throw result.exceptions.first()
            ValidationResult.Success -> {
                try {
                    val authToken = accountRepository.signIn(credential.mapToAuthCredential())
                    localAuthTokenRepository.saveToken(authToken.token)
                    return authToken.getRole()
                } catch (e: Exception) {
                    if (e is HttpException) {
                        when (e.code) {
                            401 -> throw InvalidCredentialsException()
                            404 -> throw EmailNotFoundInSystemException()
                            else -> throw e
                        }
                    } else {
                        throw e
                    }
                }
            }
        }
    }
}

