@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.electro.fish.navigation.base.NavComponentAppNavigator
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.component.AppSmallExtendedFloatingButton
import com.electro.fish.ui.theme.Dimens

@Composable
fun BottomNavigationBar(
    appNavigator: NavComponentAppNavigator,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .systemGesturesPadding()
    ) {
        HorizontalFloatingToolbar(
            expanded = false,
            trailingContent = {
                AppSmallExtendedFloatingButton(
                    text = "Start Fishing",
                    iconDrawableRes = R.drawable.fish_hook,
                    containerColor = Color(0xFF3D922E),
                    onClick = { }
                )
            },
            modifier = modifier.wrapContentWidth(),
        ) {
            AppIconButton(
                imageVector = Icons.Default.Home,
                onClick = {
                    appNavigator.launchScreen(TopLevel.Home) {
                        popUpTo(TopLevel.Home) {
                            inclusive = true
                        }
                    }
                }
            )

            AppSmallExtendedFloatingButton(
                text = "Start",
                iconDrawableRes = R.drawable.fish_hook,
                containerColor = Color(0xFF3D922E),
                onClick = {
                    appNavigator.launchScreen(TopLevel.FishingSession) {
                        popUpTo(TopLevel.FishingSession) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = Dimens.LargePadding)
            )

            AppIconButton(
                imageVector = Icons.Default.Person,
                onClick = {
                    appNavigator.launchScreen(TopLevel.Profile) {
                        popUpTo(TopLevel.Profile) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}