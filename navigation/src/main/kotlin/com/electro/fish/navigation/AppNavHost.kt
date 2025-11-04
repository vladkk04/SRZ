package com.electro.fish.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.electro.fish.navigation.base.NavComponentAppNavigator
import com.electro.fish.presentation.WelcomeScreen
import kotlinx.coroutines.awaitCancellation

@Composable
fun AppNavHost(
    modifier: Modifier,
    startScreen: Screen = WelcomeScreen
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val navController = rememberNavController()
    val appNavigator = NavComponentAppNavigator.get()

    val navGraph = remember {
        navController.createGraph(startDestination = startScreen) {
            buildAppNavGraph()
        }
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            try {
                appNavigator.setNavController(navController)
                awaitCancellation()
            } finally {
                appNavigator.setNavController(null)
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        graph = navGraph
    )
}