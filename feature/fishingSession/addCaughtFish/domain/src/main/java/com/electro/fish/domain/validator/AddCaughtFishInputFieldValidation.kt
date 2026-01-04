package com.electro.fish.domain.validator

import com.electro.essential.validator.BaseInputField
import com.electro.fish.domain.resources.AddCaughtFishStringProvider

sealed interface AddCaughtFishInputFieldValidation {

    data object TypeOfFish: BaseInputField.TextInputField<AddCaughtFishStringProvider> {
        override val fieldName: (AddCaughtFishStringProvider) -> String = { it.typeOfFish }
    }

    data object LengthInCm: BaseInputField.TextInputField<AddCaughtFishStringProvider> {
        override val fieldName: (AddCaughtFishStringProvider) -> String = { it.lengthInCm }
    }

    data object WeightInKg: BaseInputField.TextInputField<AddCaughtFishStringProvider> {
        override val fieldName: (AddCaughtFishStringProvider) -> String = { it.weightInKg }
    }
}