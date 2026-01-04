package com.electro.fish.data.fishingSession

import kotlinx.coroutines.flow.Flow

interface LocalFishingCodeProvider {
    suspend fun getCode(): String

    fun getCodeByFlow(): Flow<String>
}