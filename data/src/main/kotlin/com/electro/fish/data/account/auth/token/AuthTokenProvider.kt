package com.electro.fish.data.account.auth.token

import kotlinx.coroutines.flow.Flow

interface AuthTokenProvider {
    fun getTokenByFlow(): Flow<String>
    fun getToken(): String?
}