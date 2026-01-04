package com.electro.fish.presentation.dataFilling

import com.electro.essential.validator.InputFormState
import com.electro.fish.domain.resources.DataFillingStringProvider

interface DataFillingState {
    val isDataFillingInProgress: Boolean
    val stringProvider: DataFillingStringProvider
    val inputFormState: InputFormState
}
