@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.ui.component

import android.R.attr.enabled
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.electro.fish.core.ui.R
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
    shape: Shape = OutlinedTextFieldDefaults.shape,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
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
        shape = shape,
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        keyboardActions = keyboardActions ?: KeyboardActions(
            onGo = { onImeActionCallback.invoke() },
            onDone = { onImeActionCallback.invoke() },
            onSend = { onImeActionCallback.invoke() },
            onNext = { onImeActionCallback.invoke() },
            onSearch = { onImeActionCallback.invoke() },
            onPrevious = { onImeActionCallback.invoke() }
        ),
        visualTransformation = visualTransformation,
        colors = colors
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
    errorMessage: String? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

    AppOutlinedTextField(
        value = selectedDate?.convertMillisToDate() ?: "",
        label = label.ifEmpty { stringResource(R.string.date) },
        placeholder = ("MM/DD/YYYY"),
        errorMessage = errorMessage,
        isReadOnly = true,
        isError = isError,
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
fun AppOutlinedExposedDropdownMenuTextField(
    label: String,
    items: List<String>,
    selectedValue: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        AppOutlinedTextField(
            value = selectedValue,
            onValueChange = {
                onItemSelected(it)
                expanded = false
            },
            isReadOnly = true,
            label = label,
            leadingIcon = leadingIcon,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}





