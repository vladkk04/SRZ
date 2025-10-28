package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.presentation.signUp.navigation.SignUpNavigator
import jakarta.inject.Inject

class SignUpNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): SignUpNavigator {

}