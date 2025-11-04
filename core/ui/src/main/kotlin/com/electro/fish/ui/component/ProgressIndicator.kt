package com.electro.fish.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun AppAnimatedLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val animateProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    LinearProgressIndicator(
        progress = { animateProgress },
        modifier = modifier
    )
}