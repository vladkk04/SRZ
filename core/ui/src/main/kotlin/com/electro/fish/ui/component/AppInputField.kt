package com.electro.fish.ui.component

import android.R.attr.label
import android.util.Log.i
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.electro.fish.core.ui.R

sealed class FocusManagerAction {
    data object Next : FocusManagerAction()
    data object None : FocusManagerAction()
    data class Done(val action: (() -> Unit)? = null) : FocusManagerAction()
}

@Composable
@Preview
fun AppTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    label: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = {
            Text(
                text = label
            )
        },
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        modifier = modifier
    )
}

@Composable
@Preview
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    label: String = "",
    errorMessage: String? = null,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    focusRequester: FocusRequester? = null,
    focusManagerAction: FocusManagerAction = FocusManagerAction.Next,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val imeAction = when (focusManagerAction) {
        is FocusManagerAction.Next -> ImeAction.Next
        is FocusManagerAction.Done -> ImeAction.Done
        is FocusManagerAction.None -> ImeAction.Default
    }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { Text(text = label) },
        supportingText = {
            if (errorMessage != null) {
                Text(text = errorMessage)
            }
        },
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        singleLine = isSingleLine,
        keyboardOptions = keyboardOptions.copy(imeAction = imeAction),
        keyboardActions = keyboardActions ?: KeyboardActions(
            onDone = {
                if (focusManagerAction is FocusManagerAction.Done) {
                    focusManager.clearFocus()
                    focusManagerAction.action?.let { it() }
                } else {
                    focusManager.clearFocus()
                }
            }
        ),
        modifier = if (focusRequester != null)
            modifier.focusRequester(focusRequester)
        else modifier

    )
}

@Composable
fun AppOutlinedPasswordTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    errorMessage: String? = null,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    focusRequester: FocusRequester? = null,
    focusManagerAction: FocusManagerAction = FocusManagerAction.Next,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions? = null,
    label: String = stringResource(R.string.password)
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    AppOutlinedTextField(
        onValueChange = onValueChange,
        label = label,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
            ) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null
                )
            }
        },
        errorMessage = errorMessage,
        isError = isError,
        isSingleLine = isSingleLine,
        focusManagerAction = focusManagerAction,
        focusRequester = focusRequester,
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        keyboardActions = keyboardActions,
        modifier = modifier
    )
}
