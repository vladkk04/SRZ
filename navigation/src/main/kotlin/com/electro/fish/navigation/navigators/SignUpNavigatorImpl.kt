package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.presentation.signUp.navigation.SignUpNavigator
import jakarta.inject.Inject

class SignUpNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SignUpNavigator {
    override fun launchSignInScreen() {
        appNavigator.launchScreen(SignInScreen) {
            popUpTo(SignUpScreen) {
                inclusive = true
            }
        }
    }
}