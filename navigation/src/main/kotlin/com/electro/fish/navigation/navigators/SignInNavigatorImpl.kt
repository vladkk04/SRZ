package com.electro.fish.navigation.navigators

import androidx.compose.ui.input.key.Key.Companion.I
import com.electro.fish.navigation.ForgotPasswordScreen
import com.electro.fish.navigation.InspectorScreen
import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.TopLevel
import com.electro.fish.navigation.WelcomeScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.FishermanScreen
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

    override fun launchHomeScreen() {
        appNavigator.launchScreen(TopLevel.TopLevelGraph) {
            popUpTo(WelcomeScreen) {
                inclusive = true
            }
        }
    }


    override fun launchInspectorScreen() {
        appNavigator.launchScreen(InspectorScreen) {
            popUpTo(WelcomeScreen) {
                inclusive = true
            }
        }
    }

    override fun launchForgotPasswordScreen() {
        appNavigator.launchScreen(ForgotPasswordScreen) {

        }
    }
}