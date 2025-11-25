package com.electro.fish.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.electro.fish.feature.welcome.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.component.LogoSize
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.theme.WelcomeSignUpAsGuestButtonContainerColor
import com.electro.fish.ui.util.extension.repeatWithLifecycleResumed

@Composable
fun WelcomeScreen() {
    val viewModel = hiltViewModel<WelcomeViewModel>()

    WelcomeContent(
        onSignInClick = viewModel::launchSignInScreen,
        onSignUpClick = viewModel::launchSignUpScreen,
    )
}

@Composable
private fun WelcomeContent(
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    durationAnimation: Int = 1000
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    
    var logoSize by rememberSaveable { mutableStateOf(LogoSize.FULL) }
    var isVisibleContent by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatWithLifecycleResumed {
            if (logoSize == LogoSize.SMALL) { logoSize = LogoSize.FULL }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircle(
            logoSize,
            onAnimationFinished = { isVisibleContent = true }
        )

        Spacer(Modifier.weight(1f))

        AnimatedVisibility(
            visible = isVisibleContent,
            enter = fadeIn(animationSpec = tween(durationAnimation)),
            exit = fadeOut(animationSpec = tween(durationAnimation))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.LargePadding),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AppElevatedButton(
                    text = stringResource(R.string.welcome_sign_in),
                    onClick = {
                        onSignInClick()
                        isVisibleContent = false
                        logoSize = LogoSize.SMALL
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                AppElevatedButton(
                    text = stringResource(R.string.welcome_sign_up_as_guest),
                    onClick = {
                        onSignUpClick()
                        isVisibleContent = false
                        logoSize = LogoSize.SMALL
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
