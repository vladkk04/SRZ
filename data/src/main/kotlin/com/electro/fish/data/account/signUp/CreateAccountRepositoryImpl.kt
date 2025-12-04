package com.electro.fish.data.account.signUp

import com.electro.fish.data.CreateAccountRepository
import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.model.AuthToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateAccountRepositoryImpl @Inject constructor(
    private val api: CreateAccountApi
): CreateAccountRepository {

    override suspend fun createAccount(createAccountCredentials: CreateAccountCredentials): AuthToken {
        val request = SignUpRequestDto(
            email = createAccountCredentials.email,
            password = createAccountCredentials.password,
            role = createAccountCredentials.role,
        )

        return api.signUp(request)
    }
}