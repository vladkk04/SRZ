package com.electro.fish.presentation.pagerContent

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.electro.fish.feature.forgotPassword.presentation.R
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField

@Composable
fun ResetPasswordContent(
    onPasswordValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onDoneImeAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppOutlinedPasswordTextField(
        onValueChange = onPasswordValueChange,
        imeAction = ImeAction.Done,
        onImeAction = onDoneImeAction,
        label = stringResource(R.string.forgotPassword_password),
        modifier = modifier
    )
}