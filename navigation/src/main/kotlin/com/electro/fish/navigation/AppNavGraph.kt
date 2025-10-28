package com.electro.fish.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.electro.presentation.SignInScreen
import com.electro.presentation.signUp.SignUpScreen
import com.electro.presentation.WelcomeScreen

fun NavGraphBuilder.buildAppNavGraph() {
    composable<WelcomeScreen> { WelcomeScreen() }
    composable<SignInScreen> { SignInScreen() }
    composable<SignUpScreen> { SignUpScreen() }
}
