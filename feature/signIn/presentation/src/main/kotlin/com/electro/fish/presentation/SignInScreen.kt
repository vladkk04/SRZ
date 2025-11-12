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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.fish.domain.entity.Credentials
import com.electro.fish.feature.signIn.presentation.R
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.ContainerView
import com.electro.fish.ui.component.FocusManagerAction
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.clickableWithoutIndication

@Composable
fun SignInScreen() {
    val viewModel = hiltViewModel<SignInViewModel>()
    val containerState by viewModel.state.collectAsState()

    ContainerView(
        container = containerState
    ) { state ->
        SignInContent(
            state = state,
            onSignIn = { viewModel.onEvent(SignInEvent.SignIn(it)) },
            onNavigateToSignUpScreen = { viewModel.onEvent(SignInEvent.OnNavigateToSignUp) },
            onNavigateToForgotPasswordScreen = { viewModel.onEvent(SignInEvent.OnNavigateToForgotPassword) },
        )
    }
}

@Composable
private fun SignInContent(
    state: SignInViewModel.SignInState,
    onSignIn: (Credentials) -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit,
) {
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }

    var isFormVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { isFormVisible = true }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircle()

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
                    onSignIn = { onSignIn(Credentials(emailValue.value, passwordValue.value)) },
                    emailValue = emailValue,
                    passwordValue = passwordValue,
                    onNavigateToForgotPasswordScreen = onNavigateToForgotPasswordScreen,
                )

                Spacer(Modifier.weight(0.8f))

                BottomSignUpContent(
                    onNavigateToSignUpScreen = onNavigateToSignUpScreen,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun CenterContent(
    state: SignInViewModel.SignInState,
    emailValue: MutableState<String>,
    passwordValue: MutableState<String>,
    onSignIn: (Credentials) -> Unit,
    onNavigateToForgotPasswordScreen: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    AppOutlinedTextField(
        onValueChange = { emailValue.value = it },
        label = stringResource(R.string.signIn_email),
        errorMessage = state.errorMessages.getOrDefault(
            DefaultAuthFormInputFieldValidation.Email,
            null
        ),
        isError = state.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Email),
        focusRequester = focusRequester,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier.fillMaxWidth()
    )

    AppOutlinedPasswordTextField(
        onValueChange = { passwordValue.value = it },
        label = stringResource(R.string.signIn_password),
        errorMessage = state.errorMessages.getOrDefault(
            DefaultAuthFormInputFieldValidation.Password,
            null
        ),
        isError = state.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Password),
        focusManagerAction = FocusManagerAction.Done {
            focusRequester.freeFocus()
            onSignIn(Credentials(emailValue.value, passwordValue.value))
        },
        modifier = Modifier.fillMaxWidth()
    )

    Text(
        text = stringResource(R.string.signIn_forgot_password),
        modifier = Modifier
            .clickable(
                onClick = onNavigateToForgotPasswordScreen,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    )

    AppElevatedLoadingButton(
        text = stringResource(R.string.signIn_sign_in),
        isLoading = state.isSignInInProgress,
        onClick = {
            focusRequester.requestFocus()
            onSignIn(Credentials(emailValue.value, passwordValue.value))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.MediumPadding)
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