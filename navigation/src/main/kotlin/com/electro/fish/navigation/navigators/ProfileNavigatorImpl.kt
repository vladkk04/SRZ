package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.Profile
import com.electro.fish.navigation.WelcomeScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.ProfileNavigator
import jakarta.inject.Inject

class ProfileNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): ProfileNavigator {
    override fun launchLanguageScreen() {
        appNavigator.launchScreen(Profile.LanguageScreen) {
            launchSingleTop = true
        }
    }

    override fun launchLicenseesScreen() {
        appNavigator.launchScreen(Profile.LicenseesScreen) {
            launchSingleTop = true
        }
    }

    override fun launchWelcomeScreen() {
        appNavigator.launchScreen(WelcomeScreen) {
            popUpTo(Profile.ProfileScreen) {
                inclusive = true
            }
        }
    }
}