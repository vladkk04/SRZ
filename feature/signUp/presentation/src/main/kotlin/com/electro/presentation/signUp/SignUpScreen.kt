package com.electro.presentation.signUp

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.electro.domain.model.NewAccount
import com.electro.fish.data.account.signUp.remote.dto.Role
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.CheckBoxCircle
import com.electro.fish.ui.component.FocusManagerAction
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.theme.Dimens
import com.electro.presentation.profileSetup.ProfileSetupScreen

@Composable
fun SignUpScreen() {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileSetupScreen()

    /*SignUpContent(
        state = state,
        onSignUp = viewModel::signUp,
        onSignInClick = viewModel::launchSignInScreen,
        onClearInputsErrorMessage = viewModel::onClearInputErrorMessage
    )*/
}

@Composable
private fun SignUpContent(
    state: SignUpInState,
    onSignUp: (NewAccount) -> Unit,
    onSignInClick: () -> Unit,
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
                    text = stringResource(R.string.signUp_sign_up),
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.headlineMedium
                )

                CenterContent(
                    state = state,
                    onSignUp = onSignUp,
                    onClearInputsErrorMessage = onClearInputsErrorMessage,
                )

                Spacer(Modifier.weight(0.8f))

                BottomSignUpContent(onSignInClick = onSignInClick)
            }
        }
    }
}

@Composable
private fun CenterContent(
    state: SignUpInState,
    onSignUp: (NewAccount) -> Unit,
    onClearInputsErrorMessage: () -> Unit,
) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var repeatPasswordText by rememberSaveable { mutableStateOf("") }
    var isCheckedTermAndCondition by rememberSaveable { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    AppOutlinedTextField(
        onValueChange = { emailText = it },
        label = stringResource(R.string.signUp_email),
        errorMessage = state.emailInputErrorMessage,
        isError = state.emailInputErrorMessage != null,
        focusRequester = focusRequester,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier.fillMaxWidth()
    )

    AppOutlinedPasswordTextField(
        onValueChange = {
            passwordText = it
            if (state.passwordInputErrorMessage != null) {
                onClearInputsErrorMessage()
            }
        },
        label = stringResource(R.string.signUp_password),
        errorMessage = state.passwordInputErrorMessage,
        isError = state.passwordInputErrorMessage != null,
        focusManagerAction = FocusManagerAction.Next,
        modifier = Modifier.fillMaxWidth()
    )

    AppOutlinedPasswordTextField(
        onValueChange = { emailText = it },
        label = stringResource(R.string.signUp_repeat_password),
        errorMessage = state.emailInputErrorMessage,
        isError = state.emailInputErrorMessage != null,
        focusManagerAction = FocusManagerAction.Done {
            onSignUp(
                NewAccount(
                    firstName = "Test name",
                    lastName = "Test last name",
                    email = emailText,
                    password = passwordText,
                    repeatPassword = repeatPasswordText,
                    role = Role.FISHERMAN,
                    address = "Test address",
                    birthDate = "Test birth date",
                    fishingLicenseNumber = "Test fishing license number"
                )
            )
        },
        modifier = Modifier.fillMaxWidth()
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.ExtraSmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CheckBoxCircle(
            checked = isCheckedTermAndCondition,
            onCheckedChange = { isCheckedTermAndCondition = it },
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = stringResource(R.string.signUp_terms_and_conditions),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(
                onClick = { isCheckedTermAndCondition = !isCheckedTermAndCondition },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
        )
    }

    AppElevatedLoadingButton(
        text = stringResource(R.string.signUp_sign_up), isLoading = state.isLoading, onClick = {
            onSignUp(
                NewAccount(
                    firstName = "Test name",
                    lastName = "Test last name",
                    email = emailText,
                    password = passwordText,
                    repeatPassword = repeatPasswordText,
                    role = Role.FISHERMAN,
                    address = "Test address",
                    birthDate = "Test birth date",
                    fishingLicenseNumber = "Test fishing license number"
                )
            )
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.MediumPadding)
    )
}

@Composable
private fun BottomSignUpContent(
    onSignInClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.ExtraSmallPadding,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.signUp_already_have_an_account))
        Text(
            text = stringResource(R.string.signUp_signIn),
            fontStyle = FontStyle.Normal,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(
                onClick = onSignInClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
        )
    }
}