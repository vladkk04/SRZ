package com.electro.presentation.signUp

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.electro.essential.validator.DefaultAuthFormInputFieldValidation
import com.electro.essential.validator.InputFieldEvent
import com.electro.fish.domain.model.NewAccount
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.CheckBoxCircle
import com.electro.fish.ui.component.LogoCircleAnimatedWithLifecycle
import com.electro.fish.ui.theme.Dimens
import com.electro.fish.ui.util.extension.onFocusLost

@Composable
fun SignUpScreen() {
    val viewModel = hiltViewModel<SignUpViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SignUpContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun SignUpContent(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit = {},
) {
    var isLogoFinishedAnimation by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.ExtraLargePadding)
    ) {
        LogoCircleAnimatedWithLifecycle(
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
                    text = stringResource(R.string.signUp_sign_up),
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.headlineMedium
                )

                CenterContent(
                    state = state,
                    onEvent = onEvent
                )

                Spacer(Modifier.weight(0.8f))

                BottomSignUpContent(onSignInClick = { onEvent(SignUpEvent.OnNavigateToSignInScreen) })
            }
        }
    }
}

@Composable
private fun CenterContent(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit = {},
) {
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }

    val newAccountCredentials: () -> NewAccount = {
        NewAccount(
            email = emailValue.value,
            password = passwordValue.value
        )
    }

    val focusRequester = remember { FocusRequester() }

    TextInputFields(
        state = state,
        onEvent = onEvent,
        emailValue = emailValue,
        passwordValue = passwordValue,
        focusRequester = focusRequester
    )

    TextCheckerTermAndConditions(
        openTermsAndPrivacyPolicy = { onEvent(SignUpEvent.OpenTermsAndPrivacyPolicy) },
        onChecked = { onEvent(SignUpEvent.CheckTermsAndPrivacy) },
        modifier = Modifier.fillMaxWidth()
    )

    AppElevatedLoadingButton(
        text = stringResource(R.string.signUp_sign_up),
        isLoading = state.isSignUpInProgress,
        isEnabled = state.isTermsAndPrivacyChecked,
        onClick = { onEvent(SignUpEvent.SignUp(newAccountCredentials())) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.MediumPadding)
    )
}

@Composable
private fun TextInputFields(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    emailValue: MutableState<String>,
    passwordValue: MutableState<String>,
    focusRequester: FocusRequester,
) = with(state) {

    val newAccountCredentials: () -> NewAccount = {
        NewAccount(
            email = emailValue.value,
            password = passwordValue.value
        )
    }

    AppOutlinedTextField(
        onValueChange = {
            emailValue.value = it
            onEvent(
                SignUpEvent.InputEvent(
                    InputFieldEvent.ClearError(
                        DefaultAuthFormInputFieldValidation.Email
                    )
                )
            )
        },
        label = stringProvider.email,
        isEnabled = !isSignUpInProgress,
        isError = inputFormState.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Email),
        errorMessage = inputFormState.errorMessages[DefaultAuthFormInputFieldValidation.Email],
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        imeAction = ImeAction.Next,
        onImeAction = {
            onEvent(
                SignUpEvent.InputEvent(
                    InputFieldEvent.Validate(
                        newAccountCredentials()
                    )
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onFocusLost {
                onEvent(
                    SignUpEvent.InputEvent(
                        InputFieldEvent.EnableErrorMessages(
                            DefaultAuthFormInputFieldValidation.Email
                        )
                    )
                )
                onEvent(SignUpEvent.InputEvent(InputFieldEvent.Validate(newAccountCredentials())))
            }
    )

    AppOutlinedPasswordTextField(
        onValueChange = {
            passwordValue.value = it
            onEvent(
                SignUpEvent.InputEvent(
                    InputFieldEvent.ClearError(
                        DefaultAuthFormInputFieldValidation.Password
                    )
                )
            )
        },
        label = stringProvider.password,
        errorMessage = inputFormState.errorMessages[DefaultAuthFormInputFieldValidation.Password],
        isError = inputFormState.errorMessages.containsKey(DefaultAuthFormInputFieldValidation.Password),
        isEnabled = !state.isSignUpInProgress,
        imeAction = ImeAction.Done,
        onImeAction = { onEvent(SignUpEvent.SignUp(newAccountCredentials())) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusLost {
                onEvent(
                    SignUpEvent.InputEvent(
                        InputFieldEvent.EnableErrorMessages(
                            DefaultAuthFormInputFieldValidation.Password
                        )
                    )
                )
                onEvent(SignUpEvent.InputEvent(InputFieldEvent.Validate(newAccountCredentials())))
            }
    )
}

@Composable
private fun TextCheckerTermAndConditions(
    modifier: Modifier = Modifier,
    onChecked: (Boolean) -> Unit = {},
    openTermsAndPrivacyPolicy: () -> Unit,
) {
    var isCheckedTermAndCondition by rememberSaveable { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.ExtraSmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        CheckBoxCircle(
            checked = isCheckedTermAndCondition,
            onCheckedChange = {
                isCheckedTermAndCondition = it
                onChecked(it)
            },
            modifier = Modifier.size(24.dp)
        )

        Text(text = stringResource(R.string.signUp_i_accept_the))

        Text(
            text = stringResource(R.string.signUp_term_and_privacy_policy),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(
                onClick = openTermsAndPrivacyPolicy
            )
        )
    }
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