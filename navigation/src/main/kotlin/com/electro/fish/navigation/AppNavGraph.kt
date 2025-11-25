package com.electro.fish.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.electro.fish.presentation.AddCatchFishScreen
import com.electro.fish.presentation.ForgotPasswordScreen
import com.electro.fish.presentation.GuestScreen
import com.electro.fish.presentation.SignInScreen
import com.electro.fish.presentation.WelcomeScreen
import com.electro.presentation.profileSetup.ProfileSetupScreen
import com.electro.presentation.signUp.SignUpScreen
import com.stefanoq21.material3.navigation.bottomSheet

fun NavGraphBuilder.buildAppNavGraph() {
    composable<WelcomeScreen>(
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
    ) { WelcomeScreen() }

    composable<SignInScreen>(
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
    ) { SignInScreen() }

    composable<SignUpScreen>(
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
    ) { SignUpScreen() }

    composable<ProfileSetupScreen> { ProfileSetupScreen() }

    bottomSheet<ForgotPasswordScreen> { ForgotPasswordScreen() }

    navigation<Guest.GuestGraph>(
        startDestination = Guest.AddCatchFishScreen,
    ) {
        composable<Guest.GuestScreen> { GuestScreen() }
        bottomSheet<Guest.AddCatchFishScreen> { AddCatchFishScreen() }
    }
}
