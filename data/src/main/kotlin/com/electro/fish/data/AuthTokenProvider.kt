package com.electro.fish.data

import kotlinx.coroutines.flow.Flow

interface AuthTokenProvider {
    fun getTokenByFlow(): Flow<String>
    fun getToken(): String?
}