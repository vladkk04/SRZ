package com.electro.fish.presentation.dataFilling.pager.screen

import android.R.attr.rowCount
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
import java.time.LocalDate

@Composable
fun BirthdayScreen(
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        WheelDatePicker(
            textColor = Color(0xFFffc300),
            yearsRange = (1900 .. LocalDate.now().year),
            rowCount = 7,
            size = DpSize(maxWidth, maxHeight / 1.2f),
            selectorProperties = selectorProperties(
                enabled = true,
                color = Color(0xFFf1faee).copy(alpha = 0.2f),
                border = BorderStroke(2.dp, Color(0xFFf1faee))
            ),
            modifier = Modifier.fillMaxWidth()
        ) { snappedDateTime ->
            onDateSelected(snappedDateTime.toString())
        }
    }
}