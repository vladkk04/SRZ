package com.electro.fish.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.electro.fish.core.ui.R

enum class LogoSize { FULL, SMALL }

@Composable
fun LogoCircle(
    logoSize: LogoSize,
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
        modifier = Modifier
            .clip(CircleShape)
            .size(animatedSize)
    )
}
