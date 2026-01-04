package com.electro.fish.data.fishingSession

interface LocalFishingCodeSaver {
    suspend fun saveCode(code: String)
}