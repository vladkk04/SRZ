package com.electro.fish.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.UnfocusedBorderThickness
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults.selectorProperties
import com.commandiron.wheel_picker_compose.core.WheelTextPicker
import com.electro.fish.feature.guest.presentation.R
import com.electro.fish.ui.component.AppIcon
import com.electro.fish.ui.component.AppOutlinedDatePickerTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.theme.Dimens

@Composable
fun AddCatchFishScreen() {

    AddCatchFishContent()
}


@Composable
private fun AddCatchFishContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.SmallPadding)
    ) {
        AppOutlinedTextField(
            onValueChange = {},
            label = "Label",
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                Dimens.SmallPadding,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            AppOutlinedTextField(
                onValueChange = {},
                leadingIcon = { AppIcon(drawableResId = R.drawable.ic_ruler) },
                label = stringResource(R.string.guest_cm),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.33f)
            )

            AppOutlinedTextField(
                onValueChange = {},
                leadingIcon = { AppIcon(drawableResId = R.drawable.ic_weight) },
                label = stringResource(R.string.guest_kg),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(0.33f)
            )

            WheelQuantityPicker(
                onValueChange = {},
                modifier = Modifier
                    .weight(0.33f)
                    .offset(y = (-4).dp)
            )
        }

        AppOutlinedDatePickerTextField(
            onDateSelected = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun WheelQuantityPicker(
    onValueChange: (Int) -> Unit ,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                border = BorderStroke(
                    UnfocusedBorderThickness,
                    MaterialTheme.colorScheme.outline
                ),
                shape = OutlinedTextFieldDefaults.shape
            )
    ) {
        Column {
            AppIcon(
                imageVector = Icons.Default.ArrowDropUp,
                modifier = Modifier.size(16.dp)
            )

            AppIcon(
                imageVector = Icons.Default.ArrowDropDown,
                modifier = Modifier.size(16.dp)
            )
        }
        WheelTextPicker(
            texts = (1..100).map(Int::toString),
            size = DpSize(36.dp, 56.dp),
            rowCount = 1,
            selectorProperties = selectorProperties(
                enabled = false
            ),
            onScrollFinished = {
                onValueChange(it)
                it
            },
            modifier = Modifier
        )
        Text(
            text = stringResource(R.string.guest_qty),
            textAlign = TextAlign.Start
        )
    }
}