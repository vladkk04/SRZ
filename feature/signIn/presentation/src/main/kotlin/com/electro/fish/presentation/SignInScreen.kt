package com.electro.fish.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.feature.signIn.presentation.R
import com.electro.fish.ui.component.AppElevatedLoadingButton
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
        onSignInClick = viewModel::signIn,
        onSignUpClick = viewModel::launchSignInScreen,
        onClearInputsErrorMessage = viewModel::onClearInputErrorMessage,
    )
}

@Composable
private fun SignInContent(
    state: SignInState,
    onSignInClick: (Credentials) -> Unit,
    onSignUpClick: () -> Unit,
    onClearInputsErrorMessage: () -> Unit,
) {
    var isFormVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { isFormVisible = true }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircle(modifier = Modifier.size(64.dp))

        AnimatedVisibility(
            visible = isFormVisible,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.weight(0.2f))

                Text(
                    text = stringResource(R.string.signIn_sign_in),
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.headlineMedium
                )

                CenterContent(
                    state = state,
                    onSignIn = onSignInClick,
                    onClearInputsErrorMessage = onClearInputsErrorMessage,
                )

                Spacer(Modifier.weight(0.8f))

                BottomSignUpContent(
                    onSignUpScreen = onSignUpClick
                )
            }
        }
    }
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
        label = stringResource(R.string.signIn_email),
        errorMessage = state.emailInputFieldErrorMessage,
        isError = state.emailInputFieldErrorMessage != null,
        focusRequester = focusRequester,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier
            .fillMaxWidth()
    )

    AppOutlinedPasswordTextField(
        onValueChange = {
            passwordText = it
            if (state.passwordInputFieldErrorMessage != null) {
                onClearInputsErrorMessage()
            }
        },
        label = stringResource(R.string.signIn_password),
        errorMessage = state.passwordInputFieldErrorMessage,
        isError = state.passwordInputFieldErrorMessage != null,
        focusManagerAction = FocusManagerAction.Done {

        },
        modifier = Modifier.fillMaxWidth()
    )

    Text(text = stringResource(R.string.signIn_forgot_password))

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
private fun BottomSignUpContent(
    onSignUpScreen: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.ExtraSmallPadding,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.signIn_do_not_have_account))
        Text(
            text = stringResource(R.string.signIn_sign_up),
            fontStyle = FontStyle.Normal,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .clickable(
                    onClick = onSignUpScreen,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                )
        )
    }
}