package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.TopLevel
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.dataFilling.navigation.DataFillingNavigator
import jakarta.inject.Inject

class DataFillingNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): DataFillingNavigator {

    override fun launchHomeScreen() {
        appNavigator.launchScreen(TopLevel.TopLevelGraph) {
            popUpTo(TopLevel.Home) {
                inclusive = true
            }
        }
    }

}