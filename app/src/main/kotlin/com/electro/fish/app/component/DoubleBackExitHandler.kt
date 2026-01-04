package com.electro.fish.app.component

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import com.electro.fish.app.R
import com.electro.fish.ui.theme.Dimens
import kotlinx.coroutines.delay

@Composable
fun DoubleBackExitHandler(
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