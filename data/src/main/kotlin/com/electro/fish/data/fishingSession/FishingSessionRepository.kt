package com.electro.fish.data.fishingSession

import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoRequest
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoResponse
import kotlinx.coroutines.flow.Flow

interface FishingSessionRepository {
    suspend fun addCaughtFish(addCaughtFishDto: AddCaughtFishDtoRequest): AddCaughtFishDtoResponse

    fun getCaughtFishes(): Flow<List<AddCaughtFishDtoResponse>>
}