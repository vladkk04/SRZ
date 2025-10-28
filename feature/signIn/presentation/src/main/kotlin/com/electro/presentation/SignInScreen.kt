package com.electro.presentation

import android.R.attr.fontStyle
import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.electro.domain.entity.Credentials
import com.electro.fish.feature.signIn.presentation.R
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppLoadingButton
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.FocusManagerAction
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.theme.Dimens

@Composable
fun SignInScreen() {
    val viewModel = hiltViewModel<SignInViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignInContent(
        state = state,
        onSignIn = viewModel::signIn,
        onClearInputsErrorMessage = viewModel::onClearInputErrorMessage,
    )
}

@Composable
private fun SignInContent(
    state: SignInState,
    onSignIn: (Credentials) -> Unit,
    onClearInputsErrorMessage: () -> Unit,
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            Dimens.MediumPadding,
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.XLargePadding)

    ) {
        TopContent()

        Spacer(Modifier.weight(0.1f))

        CenterContent(
            state = state,
            onSignIn = onSignIn,
            onClearInputsErrorMessage = onClearInputsErrorMessage,
        )

        Spacer(Modifier.weight(0.8f))


        BottomSignUpContent()
    }
}

@Composable
private fun ColumnScope.TopContent() {
    LogoCircle(modifier = Modifier.size(64.dp))

    Spacer(Modifier.weight(0.1f))

    Text(
        text = stringResource(R.string.signIn_sign_in),
        modifier = Modifier.align(Alignment.Start),
        style = MaterialTheme.typography.headlineMedium
    )
}


@Composable
private fun CenterContent(
    state: SignInState,
    onSignIn: (Credentials) -> Unit,
    onClearInputsErrorMessage: () -> Unit,
) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }

    AppOutlinedTextField(
        onValueChange = {
            emailText = it
        },
        label = stringResource(R.string.signIn_sign_in),
        errorMessage = state.emailInputErrorMessage,
        isError = state.emailInputErrorMessage != null,
        focusRequester = focusRequester,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier
            .fillMaxWidth()
    )

    AppOutlinedPasswordTextField(
        onValueChange = {
            passwordText = it
            if (state.passwordInputErrorMessage != null) {
                onClearInputsErrorMessage()
            }
        },
        label = stringResource(R.string.signIn_password),
        errorMessage = state.passwordInputErrorMessage,
        isError = state.passwordInputErrorMessage != null,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier.fillMaxWidth()
    )

    Text(
        text = stringResource(R.string.signIn_forgot_password),
    )


    AppElevatedLoadingButton(
        text = stringResource(R.string.signIn_sign_in),
        isLoading = state.isLoading,
        onClick = {
            focusRequester.requestFocus()
            onSignIn(Credentials(emailText, passwordText))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.MediumPadding)
    )
}

@Composable
private fun BottomSignUpContent() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.MediumPadding,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.signIn_do_not_have_account)
        )
        Text(
            text = stringResource(R.string.signIn_sign_up),
            fontStyle = FontStyle.Normal,
            textDecoration = TextDecoration.Underline
        )
    }
}