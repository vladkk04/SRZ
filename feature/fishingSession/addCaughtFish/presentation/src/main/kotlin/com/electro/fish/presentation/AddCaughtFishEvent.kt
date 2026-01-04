package com.electro.fish.presentation

import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.AddCaughtFishCredentials

sealed interface AddCaughtFishEvent {
    data class InputEvent(val event: InputFieldEvent): AddCaughtFishEvent
    data class AddCaughtFish(val addCaughtFishCredentials: AddCaughtFishCredentials): AddCaughtFishEvent
}