package com.electro.fish.presentation.dataFilling

import android.util.Printer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.FileRepository
import com.electro.essential.TypePicture
import com.electro.essential.UrlUpload
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.validator.InputFieldEvent
import com.electro.essential.validator.InputFormState
import com.electro.fish.commonandroid.image.UriConverter
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataProvider
import com.electro.fish.data.account.auth.token.AuthTokenProvider
import com.electro.fish.domain.usecase.SignUpUseCase
import com.electro.fish.domain.exception.UserAlreadyExistException
import com.electro.fish.domain.model.NewAccountFull
import com.electro.fish.domain.resources.DataFillingStringProvider
import com.electro.fish.presentation.dataFilling.navigation.DataFillingNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import kotlin.math.sign

@HiltViewModel
class DataFillingViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val uriConverter: UriConverter,
    private val fileRepository: FileRepository,
    private val stringProvider: DataFillingStringProvider,
    private val navigator: DataFillingNavigator,
) : ViewModel() {
    private val _state = MutableStateFlow(
        DataFillingStateImpl(
            stringProvider = stringProvider,
            inputFormState = InputFormState(stringProvider)
        )
    )

    val state: StateFlow<DataFillingState> = _state.asStateFlow()

    fun onEvent(event: DataFillingEvent) {
        when (event) {
            is DataFillingEvent.InputEvent -> {
                updateFormState(event.event)
            }
        }
    }

    private fun updateFormState(event: InputFieldEvent) {
        _state.update { currentState ->
            currentState.copy(inputFormState = currentState.inputFormState.onEvent(event))
        }
    }

    fun finish(newAccount: NewAccountFull) = viewModelScope.launch {
        _state.update { it.copy(isDataFillingInProgress = true) }

        try {
            signUpUseCase.invoke(newAccount)
            fileRepository.uploadImages(
                UrlUpload.Profile,
                TypePicture.FISHING_LICENSE_PICTURE to uriConverter.toFile(newAccount.fishingLicence, "fishing_license") ,
                TypePicture.MEMBERSHIP_PICTURE to uriConverter.toFile(newAccount.fishingTicket, "fishing_ticket"),
                TypePicture.FISHING_TICKET_PICTURE to uriConverter.toFile(newAccount.membershipMark, "membership_mark"),
            )
            navigator.launchHomeScreen()
        } catch (e: BaseAppException) {
            when (e) {
                is UserAlreadyExistException -> {
                    //_state.update { it.copy(isVisibleChangeEmailDialog = true) }
                }

                else -> {
                    e.printStackTrace()
                }
            }
        } finally {
            _state.update { it.copy(isDataFillingInProgress = false) }
        }


    }

    private data class DataFillingStateImpl(
        override val isDataFillingInProgress: Boolean = false,
        override val stringProvider: DataFillingStringProvider,
        override val inputFormState: InputFormState = InputFormState(stringProvider)
    ) : DataFillingState

}