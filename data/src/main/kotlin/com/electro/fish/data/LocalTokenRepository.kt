package com.electro.fish.data

import com.electro.fish.data.model.Token

interface LocalTokenRepository {
    suspend fun saveToken(token: Token)
}