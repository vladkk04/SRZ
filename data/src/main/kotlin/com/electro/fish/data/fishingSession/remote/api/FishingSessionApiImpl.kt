package com.electro.fish.data.fishingSession.remote.api

import com.electro.fish.data.fishingSession.remote.FishingSessionApi
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoRequest
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoResponse
import com.electro.fish.data.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

class FishingSessionApiImpl @Inject constructor(
    private val client: HttpClient
): FishingSessionApi {
    override suspend fun addCaughtFish(addCaughtFishDto: AddCaughtFishDtoRequest): AddCaughtFishDtoResponse =
        client.post(NetworkConfig.CATCH_PATH_URL) { setBody(addCaughtFishDto) }.body<AddCaughtFishDtoResponse>()

    override suspend fun getCaughtFishes(): List<AddCaughtFishDtoResponse> =
        client.get(NetworkConfig.GET_CAUGHT_FISHES_PATH_URL).body<List<AddCaughtFishDtoResponse>>()
}