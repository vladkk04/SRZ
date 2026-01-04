package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.FileRepository
import com.electro.essential.TypePicture
import com.electro.essential.UrlUpload
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.commonandroid.image.UriConverter
import com.electro.fish.domain.model.AddCaughtFishCredentials
import com.electro.fish.domain.resources.AddCaughtFishStringProvider
import com.electro.fish.domain.usecase.AddCaughtFishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCaughtFishViewModel @Inject constructor(
    private val addCaughtFishUseCase: AddCaughtFishUseCase,
    private val stringProvider: AddCaughtFishStringProvider,
    private val uriConverter: UriConverter,
    private val fileRepository: FileRepository
): ViewModel() {

    private val _state = MutableStateFlow(
        AddCaughtFishStateImpl(
            stringProvider = stringProvider
        )
    )
    val state: StateFlow<AddCaughtFishState> = _state.asStateFlow()

    fun onEvent(event: AddCaughtFishEvent) {
        when (event) {
            is AddCaughtFishEvent.InputEvent -> {
                updateFormState(event.event)
            }

            is AddCaughtFishEvent.AddCaughtFish -> {
                addCaughtFish(event.addCaughtFishCredentials)
            }
        }
    }

    private fun updateFormState(event: InputFieldEvent) {
        _state.update { currentState ->
            currentState.copy(inputFormState = currentState.inputFormState.onEvent(event))
        }
    }

    private fun addCaughtFish(addCaughtFishCredentials: AddCaughtFishCredentials) = viewModelScope.launch {
        try {
            _state.update { it.copy(isAddCaughtFishInProgress = true) }

            val response = addCaughtFishUseCase.invoke(addCaughtFishCredentials)

            val file = uriConverter.toFile(addCaughtFishCredentials.image, "file")
            fileRepository.uploadImages(UrlUpload.AddFish(response.id), TypePicture.CAUGHT_FISH_PICTURE to file)

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _state.update { it.copy(isAddCaughtFishInProgress = false) }
        }
    }

    private data class AddCaughtFishStateImpl(
        override val isAddCaughtFishInProgress: Boolean = false,
        override val stringProvider: AddCaughtFishStringProvider,
        override val inputFormState: InputFormState = InputFormState(stringProvider),
    ): AddCaughtFishState
}