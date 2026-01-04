@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.app

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.electro.fish.app.component.DoubleBackExitHandler
import com.electro.fish.navigation.AppNavHost
import com.electro.fish.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreenWithCondition(viewModel.state.value.startScreenDestination != null)
        enableEdgeToEdgeWithTransparentNavigationBar()
        setContent {
            val state by viewModel.state.collectAsState()

            ApplicationTheme(isSystemInDarkTheme()) {
                if (state.startScreenDestination != null) {
                    AppNavHost(startScreen = state.startScreenDestination!!)
                }
                DoubleBackExitHandler(onExit = ::finish)
            }
        }
    }
}


private fun Activity.installSplashScreenWithCondition(condition: Boolean) {
    installSplashScreen().apply {
        setKeepOnScreenCondition { condition }
    }
}

private fun AppCompatActivity.enableEdgeToEdgeWithTransparentNavigationBar() {
    enableEdgeToEdge(
        navigationBarStyle = SystemBarStyle.auto(
            Color.Transparent.toArgb(),
            Color.Transparent.toArgb()
        )
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        window.isNavigationBarContrastEnforced = false
    }
}

