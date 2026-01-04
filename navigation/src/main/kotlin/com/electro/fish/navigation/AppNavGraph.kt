package com.electro.fish.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.electro.fish.presentation.AddCaughtFishScreen
import com.electro.fish.presentation.FishermanScreen
import com.electro.fish.presentation.ForgotPasswordScreen
import com.electro.fish.presentation.InspectorScreen
import com.electro.fish.presentation.LanguageScreen
import com.electro.fish.presentation.LicensesScreen
import com.electro.fish.presentation.ProfileScreen
import com.electro.fish.presentation.SelectFishingSpotScreen
import com.electro.fish.presentation.FishingSessionScreen
import com.electro.fish.presentation.SignInScreen
import com.electro.fish.presentation.WelcomeScreen
import com.electro.fish.presentation.dataFilling.DataFillingScreen
import com.electro.fish.presentation.signUp.SignUpScreen
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

    bottomSheet<ForgotPasswordScreen> { ForgotPasswordScreen() }

    composable<DataFillingScreen>(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(1000)
            )
        },
    ) { DataFillingScreen() }

    composable<InspectorScreen> {
        InspectorScreen()
    }

    navigation<TopLevel.TopLevelGraph>(
        startDestination = TopLevel.Home,
    ) {
        composable<TopLevel.Home> { FishermanScreen() }

        navigation<TopLevel.FishingSession>(
            startDestination = SelectFishingSpotScreen,
        ) {
            composable<SelectFishingSpotScreen> { SelectFishingSpotScreen() }
            composable<FishingSession> { FishingSessionScreen() }
            bottomSheet<AddCaughtFishScreen> { AddCaughtFishScreen() }
        }

        navigation<TopLevel.Profile>(
            startDestination = Profile.ProfileScreen,
        ) {
            composable<Profile.ProfileScreen> { ProfileScreen() }
            composable<Profile.LicenseesScreen> { LicensesScreen() }
            dialog<Profile.LanguageScreen> { LanguageScreen() }
        }
    }
}
