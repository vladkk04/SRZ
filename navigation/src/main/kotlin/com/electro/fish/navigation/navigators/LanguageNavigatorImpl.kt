package com.electro.fish.navigation.navigators

import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.presentation.navigation.LanguageNavigator
import javax.inject.Inject

class LanguageNavigatorImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : LanguageNavigator {
    override fun goBack() { appNavigator.navigateUp() }
}