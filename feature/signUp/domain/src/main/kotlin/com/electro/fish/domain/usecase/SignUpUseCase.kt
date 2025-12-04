package com.electro.fish.domain.usecase

import com.electro.essential.exception.HttpException
import com.electro.essential.validator.ValidationResult
import com.electro.fish.domain.model.NewAccount
import com.electro.fish.domain.model.mapToCreateAccountCredentials
import com.electro.fish.data.CreateAccountRepository
import com.electro.fish.data.LocalAuthTokenRepository
import com.electro.fish.domain.exception.UserAlreadyExistException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val createAccountRepository: CreateAccountRepository,
    private val localAuthTokenRepository: LocalAuthTokenRepository
) {
    suspend operator fun invoke(account: NewAccount) {
        when (val result = account.validate()) {
            is ValidationResult.Error -> throw result.exceptions.first()
            ValidationResult.Success -> {
                try {
                    val token = createAccountRepository.createAccount(account.mapToCreateAccountCredentials())
                    localAuthTokenRepository.saveToken(token.token)
                } catch (e: HttpException) {
                    when (e.code) {
                        409 -> throw UserAlreadyExistException()
                        else -> throw e
                    }
                }
            }
        }
    }
}