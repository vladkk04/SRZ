package com.electro.fish.ui.component

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MediumFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun AppButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun AppIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
            CircularWavyProgressIndicator(modifier = Modifier.size(sizeDp),)
        } else {
            Text(text = text)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
        modifier = modifier
            .height(48.dp)
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppElevatedLoadingButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var buttonHeight by remember { mutableIntStateOf(0) }

    ElevatedButton(
        onClick = onClick,
        enabled = !isLoading,
        modifier = modifier
            .height(48.dp)
            .onGloballyPositioned { buttonHeight = it.size.height },
    ) {
        if (isLoading) {
            val sizeDp = with(LocalDensity.current) { buttonHeight.toDp() * 0.5f }
            CircularWavyProgressIndicator(modifier = Modifier.size(sizeDp),)
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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


