package com.electro.fish.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.electro.fish.navigation.AppNavHost
import com.electro.fish.ui.theme.ApplicationTheme
import com.electro.fish.ui.theme.Dimens
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.value.startScreenDestination == null
            }
        }
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()

            ApplicationTheme(isSystemInDarkTheme()) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(
                        startScreen = state.startScreenDestination ?: return@Scaffold,
                        modifier = Modifier.padding(it)
                    )
                }

                DoubleBackExitHandler(onExit = ::finish)
            }
        }
    }
}

@Composable
private fun DoubleBackExitHandler(
    onExit: () -> Unit,
    message: String = stringResource(id = R.string.press_again_to_exit),
    timeoutMillis: Long = 1500
) {
    var lastBackPressed by remember { mutableLongStateOf(0L) }
    var showText by remember { mutableStateOf(false) }

    LaunchedEffect(showText) {
        if (showText) {
            delay(timeoutMillis)
            showText = false
        }
    }

    BackHandler {
        val now = System.currentTimeMillis()

        if (now - lastBackPressed <= timeoutMillis) {
            onExit()
        } else {
            showText = true
        }

        lastBackPressed = now
    }

    AnimatedVisibility(
        visible = showText,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = message,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(Dimens.SmallPadding)
                    )
                    .padding(Dimens.SmallPadding)
            )
        }
    }
}
