package com.electro.fish.domain.model

import android.net.Uri
import com.electro.essential.validator.InputFieldValue
import com.electro.essential.validator.WithFormInputValidator
import com.electro.fish.data.fishingSession.model.FishType
import com.electro.fish.data.fishingSession.remote.dto.AddCaughtFishDtoRequest
import com.electro.fish.domain.validator.AddCaughtFishInputFieldValidation
import java.time.LocalDate
import java.time.LocalDateTime

data class AddCaughtFishCredentials(
    val image: Uri,
    val typeOfFish: String,
    val length: String,
    val weight: String
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(AddCaughtFishInputFieldValidation.TypeOfFish, value = typeOfFish),
        InputFieldValue(AddCaughtFishInputFieldValidation.LengthInCm, value = length),
        InputFieldValue(AddCaughtFishInputFieldValidation.WeightInKg, value = weight)
    )
}

fun AddCaughtFishCredentials.mapToDto(fishingDistrictCode: String) = AddCaughtFishDtoRequest(
    date = LocalDateTime.now().toString(),
    fishType = FishType.valueOf(typeOfFish),
    lengthInCentimeters = length.toFloat(),
    weightInKilograms = weight.toFloat(),
    fishingDistrictCode = fishingDistrictCode
)
