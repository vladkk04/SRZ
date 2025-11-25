package com.electro.fish.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.electro.fish.ui.theme.Dimens

@Composable
fun SmallVerticalSpacer() {
    Spacer(Modifier.height(Dimens.SmallPadding))
}

@Composable
fun SmallHorizontalSpacer() {
    Spacer(Modifier.width(Dimens.SmallPadding))
}

@Composable
fun MediumVerticalSpacer() {
    Spacer(Modifier.height(Dimens.MediumPadding))
}

@Composable
fun MediumHorizontalSpacer() {
    Spacer(Modifier.width(Dimens.MediumPadding))
}

@Composable
fun LargeVerticalSpacer() {
    Spacer(Modifier.height(Dimens.LargePadding))
}

@Composable
fun LargeHorizontalSpacer() {
    Spacer(Modifier.width(Dimens.LargePadding))
}