package com.electro.fish.data.fishingSession.remote.dto

import com.electro.fish.data.fishingSession.model.FishType
import kotlinx.serialization.Serializable

@Serializable
data class AddCaughtFishDtoRequest(
    val date: String,
    val fishType: FishType,
    val weightInKilograms: Float,
    val lengthInCentimeters: Float,
    val fishingDistrictCode: String
)
