package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.FishingSession
import com.electro.fish.navigation.TopLevel
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.SelectFishingSpotNavigator
import javax.inject.Inject

class SelectFishingSpotNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SelectFishingSpotNavigator {
    override fun launchFishingSessionScreen() {
        appNavigator.launchScreen(FishingSession) {
            popUpTo(TopLevel.Home) {
                inclusive = true
            }
        }
    }
}