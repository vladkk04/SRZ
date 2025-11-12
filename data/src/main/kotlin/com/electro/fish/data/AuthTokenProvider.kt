package com.electro.fish.data

import com.electro.fish.data.model.AuthToken
import kotlinx.coroutines.flow.Flow

interface AuthTokenProvider {
    fun getTokenByFlow(): Flow<AuthToken?>
    fun getToken(): AuthToken?
}