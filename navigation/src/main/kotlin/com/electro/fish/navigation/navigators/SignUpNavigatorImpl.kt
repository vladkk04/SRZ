package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.DataFillingScreen
import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.WelcomeScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.signUp.navigation.SignUpNavigator
import jakarta.inject.Inject

class SignUpNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SignUpNavigator {
    override fun launchDataFillingScreen() {
        appNavigator.launchScreen(DataFillingScreen) {
            popUpTo(WelcomeScreen) {
                inclusive = true
            }
        }
    }

    override fun launchSignInScreen() {
        appNavigator.launchScreen(SignInScreen) {
            popUpTo(SignUpScreen) {
                inclusive = true
            }
        }
    }
}