package com.electro.fish.data

interface LocalAuthTokenRepository {
    suspend fun saveToken(authToken: String)
}