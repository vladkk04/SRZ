package com.electro.fish.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.essential.exception.mapper.ExceptionToMessageMapper.Companion.init
import com.electro.fish.data.account.auth.model.Role
import com.electro.fish.data.profile.model.ProfileData
import com.electro.fish.domain.usecase.GetProfileDataUseCase
import com.electro.fish.domain.usecase.SignOutUseCase
import com.electro.fish.presentation.model.ProfileDataUi
import com.electro.fish.presentation.model.mapToProfileDataUi
import com.electro.fish.presentation.navigation.ProfileNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: ProfileNavigator,
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    val state: StateFlow<ProfileState> = getProfileDataUseCase.invoke().map {
        ProfileStateImpl(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileStateImpl())

    fun launchLanguageScreen() {
        navigator.launchLanguageScreen()
    }

    fun launchLicenseesScreen() {
        navigator.launchLicenseesScreen()
    }

    fun signOut() = viewModelScope.launch {
        try {
            signOutUseCase.invoke()
            navigator.launchWelcomeScreen()
        } catch (e: Exception) {

        }
    }

    private class ProfileStateImpl(
        profileData: ProfileData = ProfileData(0, "", "", "", "", "", Role.GUEST, "", "", "", "", ""),
    ) : ProfileState {
        override val profileDataUi: ProfileDataUi = profileData.mapToProfileDataUi()
    }
}