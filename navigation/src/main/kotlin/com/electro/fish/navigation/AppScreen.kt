package com.electro.fish.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
interface Screen

@Stable
interface Graph: Screen

@Serializable
data object WelcomeScreen : Screen

@Serializable
data object SignInScreen : Screen

@Serializable
data object SignUpScreen : Screen

@Serializable
data object ProfileSetupScreen : Screen

@Serializable
data object ForgotPasswordScreen : Screen

@Serializable
data object ProfileScreen : Screen

@Serializable
data object SelectFishingSpot: Screen

@Serializable
sealed interface Guest: Screen {

    @Serializable
    data object GuestGraph: Graph

    @Serializable
    data object GuestScreen: Guest

    @Serializable
    data object AddCatchFishScreen : Guest
}





