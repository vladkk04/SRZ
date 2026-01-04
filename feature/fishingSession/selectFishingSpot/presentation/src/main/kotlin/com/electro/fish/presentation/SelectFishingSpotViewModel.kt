package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.fish.data.fishingSession.LocalFishingCodeSaver
import com.electro.fish.presentation.navigation.SelectFishingSpotNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectFishingSpotViewModel @Inject constructor(
    private val navigator: SelectFishingSpotNavigator,
    private val saver: LocalFishingCodeSaver
): ViewModel() {

    fun launchFishingSessionScreen(code: String) = viewModelScope.launch {
        saver.saveCode(code)
        navigator.launchFishingSessionScreen()
    }
}