@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.app

import android.app.Activity
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.electro.fish.navigation.AppNavHost
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.component.AppSmallExtendedFloatingButton
import com.electro.fish.ui.theme.ApplicationTheme
import com.electro.fish.ui.theme.Dimens
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        hideNavigationBars()
        setContent {
            // val state by viewModel.state.collectAsState()

            ApplicationTheme(isSystemInDarkTheme()) {
                Scaffold(
                    topBar = {
                       /* TopAppBar(
                            title = { Text(text = "Hello, Vladyslav") },
                            actions = {
                                AppImage(
                                    drawableResId = R.drawable.default_user_avatar,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick =  { },
                                        )
                                )
                            }
                        )*/
                    },
                    bottomBar = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = Dimens.LargePadding)
                        ) {
                            HorizontalFloatingToolbar(
                                expanded = false,
                                trailingContent = {
                                    AppSmallExtendedFloatingButton(
                                        text = "Start Fishing",
                                        iconDrawableRes = R.drawable.fish_hook,
                                        containerColor = Color(0xFF3D922E),
                                        onClick = { }
                                    )
                                },
                                modifier = Modifier.wrapContentWidth(),
                            ) {
                                AppIconButton(
                                    imageVector = Icons.Default.Home,
                                    onClick = { }
                                )

                                AppSmallExtendedFloatingButton(
                                    text = "Start",
                                    iconDrawableRes = R.drawable.fish_hook,
                                    containerColor = Color(0xFF3D922E),
                                    onClick = { },
                                    modifier = Modifier
                                        .height(48.dp)
                                        .padding(horizontal = Dimens.LargePadding)
                                )

                                AppIconButton(
                                    imageVector = Icons.Default.Person,
                                    onClick = { }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavHost(
                        // startScreen = state.startScreenDestination ?: return@Scaffold,
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

private fun Activity.hideNavigationBars() {
    getInsetsController(window, window.decorView).apply {
        hide(WindowInsetsCompat.Type.navigationBars())
        systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
