package com.electro.fish.navigation

import com.electro.fish.presentation.ProfileScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.electro.fish.presentation.AddCatchFishScreen
import com.electro.fish.presentation.ForgotPasswordScreen
import com.electro.fish.presentation.GuestScreen
import com.electro.fish.presentation.SelectFishingSpotScreen
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

    composable<ProfileSetupScreen>(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
    ) { ProfileSetupScreen() }

    composable<ProfileScreen> { ProfileScreen() }

    composable<SelectFishingSpot> { SelectFishingSpotScreen() }

    bottomSheet<ForgotPasswordScreen> { ForgotPasswordScreen() }

    navigation<Guest.GuestGraph>(
        startDestination = Guest.AddCatchFishScreen,
    ) {
        composable<Guest.GuestScreen> { GuestScreen() }
        bottomSheet<Guest.AddCatchFishScreen> { AddCatchFishScreen() }
    }
}
