package com.electro.fish.data

import com.electro.fish.data.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenProvider {
    fun getToken(): Flow<Token?>
}