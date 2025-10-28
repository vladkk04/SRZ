package com.electro.fish.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    primaryContainer = DarkPrimaryContainerColor,
    onPrimaryContainer = DarkOnPrimaryContainerColor,
    secondaryContainer = DarkSecondaryContainerColor,
    surface = DarkSurfaceColor,
    onSurface = DarkOnSurfaceColor,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceContainerLow = DarkSurfaceContainerLowColor,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    onPrimaryContainer = LightOnPrimaryContainerColor,
    primaryContainer = LightPrimaryContainerColor,
    secondaryContainer = LightSecondaryContainerColor,
    onSurface = LightOnSurfaceColor,
    surface = LightSurfaceColor,
    onSurfaceVariant = LightOnSurfaceVariant,
    surfaceContainerLow = LightSurfaceContainerLowColor,
    surfaceContainerHighest = LightSurfaceContainerHighest,
)

@Composable
fun ApplicationTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = if (darkTheme) DarkTypography else LightTypography,
        content = content
    )
}