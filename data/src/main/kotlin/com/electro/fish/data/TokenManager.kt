package com.electro.fish.data

import com.electro.fish.data.model.Token

interface TokenManager {
    suspend fun saveToken(token: Token?)
}