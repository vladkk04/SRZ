package com.electro.fish.data.account.auth.token.repository

import com.electro.fish.data.account.auth.token.AuthTokenSaver
import com.electro.fish.data.account.auth.token.LocalAuthTokenRepository
import javax.inject.Inject

internal class LocalAuthTokenRepositoryImpl @Inject constructor(
    private val authTokenSaver: AuthTokenSaver
): LocalAuthTokenRepository {
    override suspend fun saveToken(authToken: String) {
        authTokenSaver.saveToken(authToken)
    }
}