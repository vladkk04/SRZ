package com.electro.fish.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun AppIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String?= null,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}

@Composable
fun AppIcon(
    @DrawableRes drawableResId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String?= null,
) {
    Icon(
        painter = painterResource(id = drawableResId),
        contentDescription = contentDescription,
        modifier = modifier,
    )
}