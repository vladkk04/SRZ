package com.electro.fish.data

interface AuthTokenSaver {
    suspend fun saveToken(token: String?)
}