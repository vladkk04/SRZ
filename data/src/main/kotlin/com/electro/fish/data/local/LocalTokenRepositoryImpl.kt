package com.electro.fish.data.local

import com.electro.fish.data.LocalTokenRepository
import com.electro.fish.data.TokenManager
import com.electro.fish.data.model.Token
import javax.inject.Inject

internal class LocalTokenRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager
): LocalTokenRepository {

    override suspend fun saveToken(token: Token) {
        tokenManager.saveToken(token)
    }
}