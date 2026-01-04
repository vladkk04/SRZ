package com.electro.fish.data.fishingSession.remote

import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoRequest
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoResponse

interface FishingSessionApi {
    suspend fun addCaughtFish(addCaughtFishDto: AddCaughtFishDtoRequest): AddCaughtFishDtoResponse

    suspend fun getCaughtFishes(): List<AddCaughtFishDtoResponse>
}