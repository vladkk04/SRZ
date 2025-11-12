package com.electro.fish.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.electro.fish.presentation.AdminScreen
import com.electro.fish.presentation.SignInScreen
import com.electro.presentation.signUp.SignUpScreen
import com.electro.fish.presentation.WelcomeScreen
import com.electro.presentation.profileSetup.ProfileSetupScreen

fun NavGraphBuilder.buildAppNavGraph() {
    composable<WelcomeScreen>(
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
    ) { WelcomeScreen() }

    composable<SignInScreen>(
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
    ) { SignInScreen() }

    navigation<SignUp>(
        startDestination = SignUpScreen,
    ) {
        composable<SignUpScreen>(
            enterTransition = { fadeIn(animationSpec = tween(0)) },
            exitTransition = { fadeOut(animationSpec = tween(0)) },
        ) { SignUpScreen() }

        composable<ProfileSetupScreen> { ProfileSetupScreen() }
    }

    composable<AdminScreen> { AdminScreen() }
}
