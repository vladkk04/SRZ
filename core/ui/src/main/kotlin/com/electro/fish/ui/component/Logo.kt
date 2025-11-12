package com.electro.fish.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.electro.fish.core.ui.R

@Composable
fun LogoCircle(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .size(64.dp)
    )
}