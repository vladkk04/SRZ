package com.electro.fish.navigation

import kotlinx.serialization.Serializable

interface Screen

@Serializable
data object WelcomeScreen : Screen

@Serializable
data object SignInScreen : Screen

@Serializable
data object SignUpScreen : Screen

@Serializable
data object CompleteAccountSetupScreen: Screen

@Serializable
data object AdminScreen: Screen





