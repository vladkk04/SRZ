@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MediumFloatingActionButton
import androidx.compose.material3.SmallExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    buttonColors: ButtonColors = ButtonDefaults.textButtonColors(),
    icon: ImageVector? = null,
    onClick: () -> Unit = { },
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        shape = shape,
        colors = buttonColors,
        modifier = modifier
    ) {
        if (icon != null) {
            AppIcon(
                imageVector = icon,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }

        Text(text = text)
    }
}

@Composable
fun AppSmallExtendedFloatingButton(
    text: String,
    @DrawableRes iconDrawableRes: Int,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    onClick: () -> Unit = {},
) {
    SmallExtendedFloatingActionButton(
        text = { Text(text = text) },
        icon = { AppIcon(drawableResId = iconDrawableRes) },
        shape = shape,
        containerColor = containerColor,
        modifier = modifier,
        onClick = onClick,
    )
}


@Composable
fun AppTextButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    buttonColors: ButtonColors = ButtonDefaults.textButtonColors(),
    onClick: () -> Unit = { },
) {
    TextButton(
        onClick = onClick,
        enabled = isEnabled,
        shape = shape,
        colors = buttonColors,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun AppIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Composable
fun AppIconImageButton(
    @DrawableRes drawableId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null
        )
    }
}

@Composable
fun AppLoadingButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var buttonHeight by remember { mutableIntStateOf(0) }

    Button(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier.onGloballyPositioned { buttonHeight = it.size.height },
    ) {
        if (isLoading) {
            val sizeDp = with(LocalDensity.current) { buttonHeight.toDp() * 0.5f }
            CircularWavyProgressIndicator(modifier = Modifier.size(sizeDp))
        } else {
            Text(text = text)
        }
    }
}

@Composable
fun AppElevatedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    isEnabled: Boolean = true,
) {
    ElevatedButton(
        onClick = onClick,
        enabled = isEnabled,
        colors = buttonColors,
        modifier = modifier.height(48.dp)
    ) { Text(text = text) }
}

@Composable
fun AppElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.elevatedShape,
    buttonColors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    isEnabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = { },
) {
    ElevatedButton(
        onClick = onClick,
        enabled = isEnabled,
        shape = shape,
        colors = buttonColors,
        modifier = modifier.height(48.dp)
    ) { content() }
}

@Composable
fun AppElevatedLoadingButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    var buttonHeight by remember { mutableIntStateOf(0) }

    ElevatedButton(
        onClick = onClick,
        enabled = isEnabled && !isLoading,
        modifier = modifier
            .height(48.dp)
            .onGloballyPositioned { buttonHeight = it.size.height },
    ) {
        if (isLoading) {
            val sizeDp = with(LocalDensity.current) { buttonHeight.toDp() * 0.5f }
            CircularWavyProgressIndicator(modifier = Modifier.size(sizeDp))
        } else {
            Text(text = text)
        }
    }
}

@Composable
fun AppFloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}

@Composable
fun AppLargeFloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    LargeFloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}

@Composable
fun AppMediumFloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
) {
    MediumFloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
        )
    }
}


