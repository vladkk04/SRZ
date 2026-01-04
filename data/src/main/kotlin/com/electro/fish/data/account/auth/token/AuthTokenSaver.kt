package com.electro.fish.data.account.auth.token

interface AuthTokenSaver {
    suspend fun saveToken(token: String?)
}