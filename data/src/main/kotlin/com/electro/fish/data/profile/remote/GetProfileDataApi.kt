package com.electro.fish.data.profile.remote

import com.electro.fish.data.profile.remote.dto.GetProfileResponseDto

interface GetProfileDataApi {
    suspend fun getProfileData(): GetProfileResponseDto
}