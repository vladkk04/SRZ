package com.electro.fish.data.manager

import com.electro.fish.data.TokenManager
import com.electro.fish.data.TokenProvider
import com.electro.fish.data.datastore.AppDataStore
import com.electro.fish.data.model.Token
import com.electro.fish.data.network.interceptor.AuthTokenProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(
    private val dataStore: AppDataStore
): TokenProvider, TokenManager, AuthTokenProvider {
    private val tokenKey = "tokenKey"

    override fun getToken(): Flow<Token?> {
        return dataStore.getByFlow(tokenKey, null, String::class)
    }

    override suspend fun saveToken(token: Token?) {
        //if (token == null) { return dataStore.remove(tokenKey, String::class) }
        dataStore.set(tokenKey, token)
    }

    override fun provideAuthToken(): String? = runBlocking {
        dataStore.getOrNull(tokenKey, String::class)
    }
}