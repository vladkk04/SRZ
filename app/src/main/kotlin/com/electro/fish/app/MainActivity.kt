package com.electro.fish.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.electro.commonandroid.dialog.DialogHandler
import com.electro.fish.navigation.AppNavHost
import com.electro.fish.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dialogManagerExceptionHandler: DialogHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            ApplicationTheme(isSystemInDarkTheme()) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(modifier = Modifier.padding(it))
                }

                dialogManagerExceptionHandler.ErrorDialog()
            }
        }
    }
}
