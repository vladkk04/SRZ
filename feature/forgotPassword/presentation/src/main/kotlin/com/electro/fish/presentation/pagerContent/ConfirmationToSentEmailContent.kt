package com.electro.fish.presentation.pagerContent

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.electro.fish.feature.forgotPassword.presentation.R
import com.electro.fish.ui.component.AppOutlinedTextField

@Composable
fun ConfirmationToSentEmailContent(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.forgotPassword_confirmation_sent_email),
        modifier = modifier
    )

}