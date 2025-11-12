package com.electro.fish.data

import com.electro.fish.data.model.AuthToken

interface LocalAuthTokenRepository {
    suspend fun saveToken(authToken: AuthToken)
}