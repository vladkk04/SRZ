package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.AdminNavigator
import com.electro.fish.navigation.SignInScreen
import com.electro.fish.navigation.SignUpScreen
import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.WelcomeNavigator
import jakarta.inject.Inject

class AdminNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): AdminNavigator {

}