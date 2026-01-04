@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.electro.fish.navigation.base.NavComponentAppNavigator
import com.stefanoq21.material3.navigation.ModalBottomSheetLayout
import com.stefanoq21.material3.navigation.rememberBottomSheetNavigator
import kotlinx.coroutines.awaitCancellation

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startScreen: Screen = WelcomeScreen
) {
    val bottomSheetNavigator = rememberBottomSheetNavigator(skipPartiallyExpanded = true)
    val navController = rememberNavController(bottomSheetNavigator)
    val lifecycleOwner = LocalLifecycleOwner.current
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

    val backStack = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (backStack.value?.destination?.hasRoute<TopLevel.Home>() == true ||
                backStack.value?.destination?.hasRoute<Profile.ProfileScreen>() == true
            ) {
                BottomNavigationBar(appNavigator)
            }
        }
    ) {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            modifier = Modifier.fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                graph = navGraph,
                modifier = modifier.padding(it)
            )
        }
    }
}