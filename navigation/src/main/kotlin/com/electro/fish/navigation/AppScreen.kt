package com.electro.fish.navigation

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Stable
interface Screen

@Serializable
data object WelcomeScreen : Screen

@Serializable
data object SignInScreen : Screen

@Serializable
data object SignUp: Screen

/*This below to SignUp Nested Graph*/
@Serializable
data object SignUpScreen : Screen

/*This below to SignUp Nested Graph*/
@Serializable
data object ProfileSetupScreen : Screen

@Serializable
data object ForgotPasswordScreen : Screen

@Serializable
data object AdminScreen: Screen

@Serializable
data object HomeScreen : Screen





