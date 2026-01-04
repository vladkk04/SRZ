package com.electro.fish.data.account.auth.token

interface LocalAuthTokenRepository {
    suspend fun saveToken(authToken: String)
}