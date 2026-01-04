package com.electro.fish.data.profile.remote.api

import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.profile.remote.GetProfileDataApi
import com.electro.fish.data.profile.remote.dto.GetProfileResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class GetProfileDataApiImpl @Inject constructor(
    private val client: HttpClient
): GetProfileDataApi {
    override suspend fun getProfileData(): GetProfileResponseDto =
        client.get(NetworkConfig.GET_PROFILE_DATA_PATH_URL).body<GetProfileResponseDto>()
}