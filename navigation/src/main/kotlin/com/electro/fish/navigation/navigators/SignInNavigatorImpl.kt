package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.presentation.navigation.SignInNavigator
import jakarta.inject.Inject

class SignInNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SignInNavigator {

}