package com.electro.fish.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.feature.signIn.presentation.R
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.LogoCircleAnimatedWithLifecycle
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun SignInScreen() {
    val viewModel = hiltViewModel<SignInViewModel>()
    val state by viewModel.state.collectAsState()

    SignInContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun SignInContent(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
) {
    var isLogoFinishedAnimation by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircleAnimatedWithLifecycle (
            onAnimationFinished = { isLogoFinishedAnimation = true }
        )

        AnimatedVisibility(
            visible = isLogoFinishedAnimation,
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
                    onEvent = onEvent
                )

                Spacer(Modifier.weight(0.8f))

                BottomSignUpContent(
                    onNavigateToSignUpScreen = { onEvent(SignInEvent.OnNavigateToSignUp) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun CenterContent(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }

    val credentials: () -> SignInCredentials = {
        SignInCredentials(
            email = emailValue.value,
            password = passwordValue.value
        )
    }

    TextInputFields(
        state = state,
        onEvent = onEvent,
        emailValue = emailValue,
        passwordValue = passwordValue,
        focusRequester = focusRequester
    )

    TextForgotPassword(
        onNavigateToForgotPasswordScreen = { onEvent(SignInEvent.OnNavigateToForgotPassword) },
    )

    return AppElevatedLoadingButton(
        text = stringResource(R.string.signIn_sign_in),
        isLoading = state.isSignInInProgress,
        onClick = {
            if (emailValue.value.isEmpty()) {
                focusRequester.captureFocus()
            } else {
                focusRequester.freeFocus()
            }
            onEvent(SignInEvent.SignIn(credentials()))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.MediumPadding)
    )
}

@Composable
private fun TextInputFields(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
    emailValue: MutableState<String>,
    passwordValue: MutableState<String>,
    focusRequester: FocusRequester,
) = with(state) {

    val credentials: () -> SignInCredentials = {
        SignInCredentials(
            email = emailValue.value,
            password = passwordValue.value
        )
    }

    AppOutlinedTextField(
        onValueChange = {
            emailValue.value = it
            onEvent(SignInEvent.InputEvent(InputFieldEvent.ClearError(DefaultAuthFormInputFieldValidation.Email)))
        },
        label = stringProvider.email,
        isEnabled = !isSignInInProgress,
        isError = inputFormState.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Email),
        errorMessage = inputFormState.errorMessages[DefaultAuthFormInputFieldValidation.Email],
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        imeAction = ImeAction.Next,
        onImeAction = { onEvent(SignInEvent.InputEvent(InputFieldEvent.Validate(credentials()))) },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusLost {
                onEvent(SignInEvent.InputEvent(InputFieldEvent.EnableErrorMessages(DefaultAuthFormInputFieldValidation.Email)))
                onEvent(SignInEvent.InputEvent(InputFieldEvent.Validate(credentials())))
            }
    )

    AppOutlinedPasswordTextField(
        onValueChange = {
            passwordValue.value = it
            onEvent(SignInEvent.InputEvent(InputFieldEvent.ClearError(DefaultAuthFormInputFieldValidation.PasswordWithoutRegex)))
        },
        label = stringProvider.password,
        errorMessage = inputFormState.errorMessages[DefaultAuthFormInputFieldValidation.PasswordWithoutRegex],
        isError = inputFormState.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.PasswordWithoutRegex),
        isEnabled = !state.isSignInInProgress,
        imeAction = ImeAction.Done,
        onImeAction = { onEvent(SignInEvent.SignIn(credentials())) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusLost {
                onEvent(SignInEvent.InputEvent(InputFieldEvent.EnableErrorMessages(DefaultAuthFormInputFieldValidation.PasswordWithoutRegex)))
                onEvent(SignInEvent.InputEvent(InputFieldEvent.Validate(credentials())))
            }
    )
}

@Composable
private fun TextForgotPassword(
    modifier: Modifier = Modifier,
    onNavigateToForgotPasswordScreen: () -> Unit = {},
) {
    Text(
        text = stringResource(R.string.signIn_forgot_password),
        modifier = modifier.clickableWithoutIndication(onNavigateToForgotPasswordScreen)
    )
}

@Composable
private fun BottomSignUpContent(
    modifier: Modifier = Modifier,
    onNavigateToSignUpScreen: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.ExtraSmallPadding,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.signIn_do_not_have_account))
        Text(
            text = stringResource(R.string.signIn_sign_up),
            fontStyle = FontStyle.Normal,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickableWithoutIndication(onClick = onNavigateToSignUpScreen)
        )
    }
}