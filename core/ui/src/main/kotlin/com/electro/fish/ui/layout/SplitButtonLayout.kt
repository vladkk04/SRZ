@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.electro.fish.ui.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun AppSplitElevatedButtonLayoutWithDropdownMenu(
    leadingButtonText: String,
    trailingButtonText: String,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    onLeadingButtonClick: () -> Unit,
    onTrailingButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    colorsTrailingButton: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    size: Dp = SplitButtonDefaults.MediumContainerHeight
) {
    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.ElevatedLeadingButton(
                onClick = onLeadingButtonClick,
                modifier = Modifier.heightIn(size),
                shapes = SplitButtonDefaults.leadingButtonShapesFor(size),
                contentPadding = SplitButtonDefaults.leadingButtonContentPaddingFor(size),
            ) {
                leadingIcon()

                Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))

                Text(
                    text = leadingButtonText,
                    style = ButtonDefaults.textStyleFor(size)
                )
            }
        },
        trailingButton = {
            SplitButtonDefaults.ElevatedTrailingButton(
                checked = false,
                onCheckedChange = { onTrailingButtonClick() },
                shapes = SplitButtonDefaults.trailingButtonShapesFor(size),
                contentPadding = SplitButtonDefaults.trailingButtonContentPaddingFor(size),
                colors = colorsTrailingButton,
                modifier = Modifier.heightIn(size),
            ) {
                Text(
                    text = trailingButtonText,
                    style = ButtonDefaults.textStyleFor(size)
                )

                Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))

                trailingIcon()
            }
        },
        modifier = modifier
    )
}