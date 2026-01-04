package com.electro.fish.domain.usecase

import com.electro.essential.exception.HttpException
import com.electro.fish.data.account.auth.signUp.CreateAccountRepository
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataProvider
import com.electro.fish.data.account.auth.token.LocalAuthTokenRepository
import com.electro.fish.domain.exception.UserAlreadyExistException
import com.electro.fish.domain.model.NewAccountFull
import com.electro.fish.domain.model.mapToCreateAccountCredentials
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val createAccountRepository: CreateAccountRepository,
    private val localAuthTokenRepository: LocalAuthTokenRepository,
    private val localAccountTempDataProvider: LocalAccountTempDataProvider,
) {
    suspend operator fun invoke(account: NewAccountFull) {
        try {
            val email = localAccountTempDataProvider.getEmail()
            val password = localAccountTempDataProvider.getPassword()

            val token = createAccountRepository.createAccount(account.mapToCreateAccountCredentials(email, password))
            localAuthTokenRepository.saveToken(token.token)
        } catch (e: HttpException) {
            when (e.code) {
                409 -> throw UserAlreadyExistException()
                else -> throw e
            }
        }
    }
}