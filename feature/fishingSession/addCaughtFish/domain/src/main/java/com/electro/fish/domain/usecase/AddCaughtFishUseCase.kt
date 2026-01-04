package com.electro.fish.domain.usecase

import android.util.Log
import com.electro.essential.exception.HttpException
import com.electro.fish.data.fishingSession.FishingSessionRepository
import com.electro.fish.data.fishingSession.LocalFishingCodeProvider
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoResponse
import com.electro.fish.domain.model.AddCaughtFishCredentials
import com.electro.fish.domain.model.mapToDto
import javax.inject.Inject

class AddCaughtFishUseCase @Inject constructor(
    private val fishingSessionRepository: FishingSessionRepository,
    private val localFishingCodeProvider: LocalFishingCodeProvider,

) {
    suspend operator fun invoke(addCaughtFishCredentials: AddCaughtFishCredentials): AddCaughtFishDtoResponse {
        return try {
            val code = localFishingCodeProvider.getCode()
            fishingSessionRepository.addCaughtFish(addCaughtFishCredentials.mapToDto(code))
        } catch (e: HttpException) {
            e.printStackTrace()
            throw e
        }
    }
}