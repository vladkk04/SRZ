@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.electro.fish.core.ui.R
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.convertMillisToDate

@Composable
@Preview
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    label: String? = null,
    placeholder: String? = null,
    errorMessage: String? = null,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = true,
    isReadOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var text by rememberSaveable(value) { mutableStateOf(value) }

    val focusManager = LocalFocusManager.current

    val onImeActionCallback = {
        if (imeAction == ImeAction.Next) {
            focusManager.moveFocus(FocusDirection.Next)
        } else {
            focusManager.clearFocus()
        }
        onImeAction?.invoke()
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        modifier = modifier,
        label = { if (label != null) Text(text = label) },
        placeholder = { if (placeholder != null) Text(text = placeholder) },
        supportingText = {
            if (errorMessage != null) { Text(text = errorMessage) }
            supportingText?.invoke()
        },
        readOnly = isReadOnly,
        isError = isError,
        enabled = isEnabled,
        singleLine = isSingleLine,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        keyboardActions = keyboardActions ?: KeyboardActions(
            onGo = { onImeActionCallback.invoke() },
            onDone = { onImeActionCallback.invoke() },
            onSend = { onImeActionCallback.invoke() },
            onNext = { onImeActionCallback.invoke() },
            onSearch = { onImeActionCallback.invoke() },
            onPrevious = { onImeActionCallback.invoke() }
        ),
        visualTransformation = visualTransformation
    )
}

@Composable
fun AppOutlinedPasswordTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    label: String = stringResource(R.string.password),
    errorMessage: String? = null,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions? = null
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    AppOutlinedTextField(
        onValueChange = onValueChange,
        label = label,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            AppIconButton(
                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                onClick = { passwordVisible = !passwordVisible },
            )
        },
        errorMessage = errorMessage,
        isError = isError,
        isEnabled = isEnabled,
        isSingleLine = isSingleLine,
        imeAction = imeAction,
        onImeAction = onImeAction,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        keyboardActions = keyboardActions,
        modifier = modifier
    )
}

@Composable
fun AppOutlinedDatePickerTextField(
    onDateSelected: (Long?) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

    AppOutlinedTextField(
        value = selectedDate?.convertMillisToDate() ?: "",
        label = label.ifEmpty { stringResource(R.string.date) },
        placeholder = ("MM/DD/YYYY"),
        leadingIcon = { if (leadingIcon != null) leadingIcon() else AppIcon(Icons.Default.DateRange) },
        modifier = modifier
            .pointerInput(selectedDate) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            }
    )

    if (showModal) {
        DatePickerModal(
            onDateSelected = {
                onDateSelected(it)
                selectedDate = it
            },
            onDismiss = { showModal = false }
        )
    }
}

@Composable
fun AppOutlinedOtpTextField(
    modifier: Modifier = Modifier,
) {
    val textFieldState = rememberTextFieldState()

    @Composable
    fun NumberContainer(
        number: Int?,
        size: Dp = 48.dp,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
        borderWidth: Dp = 1.dp,
        shape: Shape = RoundedCornerShape(Dimens.SmallPadding)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
                .background(backgroundColor, shape)
        ) {
            Text(
                text = number?.toString() ?: "",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }

    BasicTextField(
        state = textFieldState,
        inputTransformation = InputTransformation.maxLength(4),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(4) { index ->
                    val char = textFieldState.text.getOrNull(index)
                    NumberContainer(
                        number = char?.digitToIntOrNull(),
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun <T> AppExposedDropdownMenuTextField(
    label: String,
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    itemLabel: (T) -> String = { it.toString() }
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        AppOutlinedTextField(
            value = selectedItem?.let { itemLabel(it) } ?: "",
            onValueChange = {},
            label = "fffd",
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                /*.menuAnchor()*/
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(itemLabel(item)) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}





