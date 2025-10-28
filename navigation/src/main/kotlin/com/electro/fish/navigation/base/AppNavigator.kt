package com.electro.fish.navigation.base

import androidx.navigation.NavOptionsBuilder
import com.electro.fish.navigation.Screen

interface AppNavigator {

    fun launchScreen(screen: Screen, navOptionsBuilder: NavOptionsBuilder.() -> Unit = {})

    fun navigateUp()
}