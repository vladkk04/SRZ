package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.ForgotPasswordNavigator
import javax.inject.Inject

class ForgotPasswordNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): ForgotPasswordNavigator {
    override fun navigateBack() {
        appNavigator.navigateUp()
    }
}