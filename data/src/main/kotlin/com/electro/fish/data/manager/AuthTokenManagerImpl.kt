package com.electro.fish.data.manager

import com.electro.fish.data.AuthTokenProvider
import com.electro.fish.data.AuthTokenSaver
import com.electro.fish.data.datastore.AppDataStore
import com.electro.fish.data.model.AuthToken
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthTokenManagerImpl @Inject constructor(
    private val dataStore: AppDataStore
): AuthTokenProvider, AuthTokenSaver, AuthInterceptionTokenProvider {

    private val tokenKey = "authTokenKey"

    override fun getTokenByFlow(): Flow<AuthToken?> =
        dataStore.getByFlow(tokenKey, null, String::class)

    override fun getToken(): AuthToken? =
        runBlocking { dataStore.getOrNull(tokenKey, String::class) }

    override suspend fun saveToken(token: AuthToken?) {
        if (token == null) { return dataStore.remove(tokenKey, String::class) }
        dataStore.set(tokenKey, token)
    }

    override fun provideToken(): String? = getToken()
}