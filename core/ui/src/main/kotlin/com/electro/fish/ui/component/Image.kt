package com.electro.fish.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun AppImage(
    @DrawableRes drawableResId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(id = drawableResId),
        contentDescription = contentDescription,
        modifier = modifier
    )
}