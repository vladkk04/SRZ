package com.electro.fish.ui.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.electro.fish.core.ui.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Calendar

sealed interface DefaultSelectableDate: SelectableDates {
    object FromCurrentDate : DefaultSelectableDate {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val todayStartMillis = LocalDate.now(ZoneOffset.UTC)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli()

            return utcTimeMillis >= todayStartMillis
        }

        override fun isSelectableYear(year: Int): Boolean {
            return year >= LocalDate.now().year
        }
    }
}

@Composable
fun DatePickerModal(
    defaultSelectableDate: DefaultSelectableDate = DefaultSelectableDate.FromCurrentDate,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        selectableDates = defaultSelectableDate
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            AppTextButton(text = stringResource(R.string.ok)) {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }
        },
        dismissButton = {
            AppTextButton(
                text = stringResource(R.string.cancel),
                onClick = onDismiss
            )
        }
    ) {
        DatePicker(state = datePickerState)
    }
}