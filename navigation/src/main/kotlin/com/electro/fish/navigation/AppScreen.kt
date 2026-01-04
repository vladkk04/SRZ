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
data object DataFillingScreen : Screen

@Serializable
data object ForgotPasswordScreen : Screen

@Serializable
data object InspectorScreen: Screen

@Serializable
data object SelectFishingSpotScreen : Screen

@Serializable
data object FishingSession: Screen

@Serializable
data object AddCaughtFishScreen : Screen

@Serializable
sealed interface Profile : Screen {

    @Serializable
    data object ProfileScreen : Profile

    @Serializable
    data object EditProfileScreen : Profile

    @Serializable
    data object LanguageScreen : Screen

    @Serializable
    data object LicenseesScreen : Profile
}

@Serializable
sealed interface TopLevel : Screen {

    @Serializable
    data object TopLevelGraph : Graph

    @Serializable
    data object Home : TopLevel

    @Serializable
    data object Profile : TopLevel

    @Serializable
    data object FishingSession : TopLevel
}




