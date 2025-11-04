package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.SignInNavigator
import jakarta.inject.Inject

class SignInNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SignInNavigator {
    override fun launchSignUpScreen() {
        appNavigator.launchScreen(SignUpScreen) {
            popUpTo(SignInScreen) {
                inclusive = true
            }
        }
    }
}