package com.electro.fish.data.fishingSession.remote.dto

import com.electro.fish.data.fishingSession.model.FishType
import kotlinx.serialization.Serializable

@Serializable
data class AddCaughtFishDtoResponse(
    val id: Int,
    val date: String,
    val fishType: FishType,
    val weightInKilograms: Int,
    val lengthInCentimeters: Int,
    val fishingDistrictCode: String,
    val catchPhotoUrl: String?,
    val catcherId: Int
)
