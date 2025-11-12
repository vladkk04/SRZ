package com.electro.fish.data

import com.electro.fish.data.model.AuthToken
import kotlinx.coroutines.flow.Flow

interface AuthTokenSaver {
    suspend fun saveToken(token: AuthToken?)
}