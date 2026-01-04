package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import com.electro.fish.navigation.FishingSessionNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FishingSessionViewModel @Inject constructor(
    private val navigator: FishingSessionNavigator
): ViewModel() {

    fun launchAddCaughtFishScreen() {
        navigator.launchAddCaughtFishScreen()
    }

}