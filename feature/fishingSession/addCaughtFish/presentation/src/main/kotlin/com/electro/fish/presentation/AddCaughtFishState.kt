package com.electro.fish.presentation

import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.resources.AddCaughtFishStringProvider

interface AddCaughtFishState {
    val isAddCaughtFishInProgress: Boolean
    val stringProvider: AddCaughtFishStringProvider
    val inputFormState: InputFormState
}