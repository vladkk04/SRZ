package com.electro.fish.data.local

import com.electro.fish.data.AuthTokenSaver
import com.electro.fish.data.LocalAuthTokenRepository
import com.electro.fish.data.model.AuthToken
import javax.inject.Inject

internal class LocalAuthTokenRepositoryImpl @Inject constructor(
    private val authTokenSaver: AuthTokenSaver
): LocalAuthTokenRepository {

    override suspend fun saveToken(authToken: AuthToken) {
        authTokenSaver.saveToken(authToken)
    }
}