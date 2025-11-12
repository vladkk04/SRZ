package com.electro.fish.data.account.signIn

import com.electro.fish.data.AccountRepository
import com.electro.fish.data.account.signIn.model.AuthCredential
import com.electro.fish.data.account.signIn.remote.AccountApi
import com.electro.fish.data.account.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.model.AuthToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AccountRepositoryImpl @Inject constructor(
    private val api: AccountApi
): AccountRepository {
    override suspend fun signIn(credential: AuthCredential): AuthToken {
        val request = SignInRequestDto(
            email = credential.email,
            password = credential.password
        )
        return api.signIn(request).authToken
    }
}