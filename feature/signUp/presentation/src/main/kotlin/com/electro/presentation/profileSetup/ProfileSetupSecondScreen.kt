package com.electro.presentation.profileSetup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults.selectorProperties

@Composable
fun ProfileSetupSecondScreen(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
    ) {
        WheelDatePicker(
            textColor = Color(0xFFffc300),
            rowCount = 7,
            size = DpSize(maxWidth, maxHeight),
            selectorProperties = selectorProperties(
                enabled = true,
                color = Color(0xFFf1faee).copy(alpha = 0.2f),
                border = BorderStroke(2.dp, Color(0xFFf1faee))
            ),
            modifier = Modifier
        ) { snappedDateTime ->

        }
    }
}