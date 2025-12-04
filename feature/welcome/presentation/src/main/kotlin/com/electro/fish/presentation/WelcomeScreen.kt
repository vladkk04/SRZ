package com.electro.fish.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.electro.fish.feature.welcome.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.component.LogoSize
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.repeatWithLifecycleResumed

@Composable
fun WelcomeScreen() {
    val viewModel = hiltViewModel<WelcomeViewModel>()

    WelcomeContent(onEvent = viewModel::onEvent)
}

@Composable
private fun WelcomeContent(
    onEvent: (WelcomeEvent) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    
    var logoSize by rememberSaveable { mutableStateOf(LogoSize.FULL) }
    var isVisibleContent by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatWithLifecycleResumed {
            if (logoSize == LogoSize.SMALL) { logoSize = LogoSize.FULL }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircle(
            logoSize,
            onAnimationFinished = { isVisibleContent = true },
            modifier = Modifier.align(Alignment.TopCenter)
        )

        AnimatedVisibility(
            visible = isVisibleContent,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.LargePadding),
                modifier = Modifier.fillMaxWidth()
            ) {
                AppElevatedButton(
                    text = stringResource(R.string.welcome_sign_in),
                    onClick = {
                        onEvent(WelcomeEvent.SignIn)
                        isVisibleContent = false
                        logoSize = LogoSize.SMALL
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                AppElevatedButton(
                    text = stringResource(R.string.welcome_sign_up_as_guest),
                    onClick = {
                        onEvent(WelcomeEvent.SignUp)
                        isVisibleContent = false
                        logoSize = LogoSize.SMALL
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
