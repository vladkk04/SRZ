package com.electro.fish.presentation.dataFilling

import com.electro.essential.validator.InputFieldEvent

sealed interface DataFillingEvent {
    data class InputEvent(val event: InputFieldEvent): DataFillingEvent
}