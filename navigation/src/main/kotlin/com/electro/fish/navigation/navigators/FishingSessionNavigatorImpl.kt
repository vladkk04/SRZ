package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.AddCaughtFishScreen
import com.electro.fish.navigation.FishingSessionNavigator
import com.electro.fish.navigation.base.AppNavigator
import javax.inject.Inject

class FishingSessionNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): FishingSessionNavigator {
    override fun launchAddCaughtFishScreen() {
        appNavigator.launchScreen(AddCaughtFishScreen) {
            launchSingleTop = true
        }
    }
}