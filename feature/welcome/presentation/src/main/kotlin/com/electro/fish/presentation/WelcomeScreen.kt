package com.electro.fish.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.electro.fish.feature.welcome.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.theme.WelcomeSignUpAsGuestButtonContainerColor

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
    var isStartAnimation by rememberSaveable { mutableStateOf(false) }
    var navigateTo by rememberSaveable { mutableStateOf<(() -> Unit)?>(null) }

    val logoSize by animateDpAsState(
        targetValue = if (isStartAnimation) 64.dp else 192.dp,
        animationSpec = tween(durationMillis = durationAnimation, easing = FastOutSlowInEasing),
        finishedListener = {
            navigateTo?.invoke()
            navigateTo = null
        }
    )

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            if(isStartAnimation) { isStartAnimation = false }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircle(modifier = Modifier.size(logoSize))

        Spacer(Modifier.weight(1f))

        AnimatedVisibility(
            visible = !isStartAnimation,
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
                        navigateTo = onSignInClick
                        isStartAnimation = true
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                AppElevatedButton(
                    text = stringResource(R.string.welcome_sign_up_as_guest),
                    onClick = {
                        navigateTo = onSignUpClick
                        isStartAnimation = true
                    },
                    buttonColors = ButtonDefaults.elevatedButtonColors().copy(
                        containerColor = WelcomeSignUpAsGuestButtonContainerColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}