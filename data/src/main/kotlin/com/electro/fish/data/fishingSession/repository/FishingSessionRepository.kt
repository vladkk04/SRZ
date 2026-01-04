package com.electro.fish.data.fishingSession.repository

import com.electro.fish.data.fishingSession.FishingSessionRepository
import com.electro.fish.data.fishingSession.remote.FishingSessionApi
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoRequest
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FishingSessionRepositoryImpl @Inject constructor(
    private val fishingSessionApi: FishingSessionApi
): FishingSessionRepository {
    override suspend fun addCaughtFish(addCaughtFishDto: AddCaughtFishDtoRequest): AddCaughtFishDtoResponse {
        return fishingSessionApi.addCaughtFish(addCaughtFishDto)
    }

    override fun getCaughtFishes(): Flow<List<AddCaughtFishDtoResponse>> = flow {
        emit(fishingSessionApi.getCaughtFishes())
    }
}