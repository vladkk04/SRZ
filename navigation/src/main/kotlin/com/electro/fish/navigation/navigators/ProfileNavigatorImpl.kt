package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.ProfileNavigator
import jakarta.inject.Inject

class ProfileNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
): ProfileNavigator {

}