package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.DataFillingScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.WelcomeNavigator
import jakarta.inject.Inject

class WelcomeNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : WelcomeNavigator {
    override fun launchSignInScreen() {
        appNavigator.launchScreen(SignInScreen)
    }

    override fun launchSignUpScreen() {
        appNavigator.launchScreen(SignUpScreen)
    }
}