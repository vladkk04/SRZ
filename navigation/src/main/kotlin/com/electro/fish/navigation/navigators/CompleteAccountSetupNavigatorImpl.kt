package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.presentation.completeAccountSetup.navigation.CompleteAccountSetupNavigator
import com.electro.presentation.signUp.navigation.SignUpNavigator
import jakarta.inject.Inject

class CompleteAccountSetupNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): CompleteAccountSetupNavigator {

}