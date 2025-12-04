package com.electro.fish.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.electro.fish.core.ui.R

enum class LogoSize { FULL, SMALL }

@Composable
fun LogoCircle(
    logoSize: LogoSize,
    modifier: Modifier = Modifier,
    durationAnimation: Int = 1000,
    onAnimationFinished: () -> Unit = {}
) {
    val targetDp = when (logoSize) {
        LogoSize.FULL -> 192.dp
        LogoSize.SMALL -> 64.dp
    }

    val animatedSize by animateDpAsState(
        targetValue = targetDp,
        animationSpec = tween(durationAnimation),
        finishedListener = { onAnimationFinished() }
    )

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .size(animatedSize)
    )
}

@Composable
fun LogoCircleAnimatedWithLifecycle(
    logoSize: LogoSize = LogoSize.FULL,
    modifier: Modifier = Modifier,
    durationAnimation: Int = 1000,
    onAnimationFinished: () -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var logoSize by rememberSaveable { mutableStateOf(logoSize) }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            logoSize = LogoSize.SMALL
        }
    }

    val targetDp = when (logoSize) {
        LogoSize.FULL -> 192.dp
        LogoSize.SMALL -> 64.dp
    }

    val animatedSize by animateDpAsState(
        targetValue = targetDp,
        animationSpec = tween(durationAnimation),
        finishedListener = { onAnimationFinished() }
    )

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .size(animatedSize)
    )
}
