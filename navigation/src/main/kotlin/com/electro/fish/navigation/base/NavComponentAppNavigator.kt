package com.electro.fish.navigation.base

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.electro.fish.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NavComponentAppNavigator @Inject constructor(): AppNavigator {

    private var navController: NavController? = null
    private val commands = mutableListOf<(NavController) -> Unit>()

    override fun launchScreen(screen: Screen, navOptionsBuilder: NavOptionsBuilder.() -> Unit) = execute {
        it.navigate(screen) {
            navOptionsBuilder(this)
        }
    }

    override fun navigateUp() = execute {
        it.navigateUp()
    }

    fun setNavController(navController: NavController?) {
        this.navController = navController
        if (navController != null) {
            commands.forEach {
                it(navController)
            }
            commands.clear()
        }
    }

    private fun execute(command: (NavController) -> Unit) {
        val navController = navController
        if (navController == null) {
            commands.add(command)
        } else {
            command(navController)
        }
    }

    @HiltViewModel
    class VM @Inject constructor(
        val appRouter: NavComponentAppNavigator
    ) : ViewModel()

    companion object {
        @Composable
        fun get(): NavComponentAppNavigator = hiltViewModel<VM>().appRouter
    }
}