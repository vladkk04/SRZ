@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.forgotPassword.presentation.R
import com.electro.fish.presentation.container.ForgotPasswordPagerContainer
import com.electro.fish.presentation.container.ForgotPasswordPagerScreenFeature
import com.electro.fish.presentation.pagerContent.EmailSendContent
import com.electro.fish.presentation.pagerContent.ResetPasswordContent
import com.electro.fish.presentation.pagerContent.VerificationCodeContent
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppHorizontalPager
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.component.MediumVerticalSpacer
import com.electro.fish.ui.theme.Dimens
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ForgotPasswordScreen() {
    val viewModel = hiltViewModel<ForgotPasswordViewModel>()

    val focusRequester = remember { FocusRequester() }

    val pagerScreens = persistentListOf(
        ForgotPasswordPagerScreenFeature(
            titleResId = R.string.forgotPassword_forgot_password,
            descriptionResId = R.string.forgotPassword_email_description,
            content = {
                EmailSendContent(
                    onEmailValueChange = { },
                    focusRequester = focusRequester,
                    onSendImeAction = this::requestNext,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
        ForgotPasswordPagerScreenFeature(
            titleResId = R.string.forgotPassword_enter_digits_code,
            descriptionResId = R.string.forgotPassword_otp_description,
            content = {
                VerificationCodeContent(
                    onCodeValueChange = { },
                    focusRequester = focusRequester,
                    onNextImeAction = this::requestNext,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.ExtraLargePadding)
                )
            }
        ),
        ForgotPasswordPagerScreenFeature(
            titleResId = R.string.forgotPassword_reset_password,
            descriptionResId = R.string.forgotPassword_reset_password_description,
            content = {
                ResetPasswordContent(
                    onPasswordValueChange = { },
                    focusRequester = focusRequester,
                    onDoneImeAction = {

                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ),
    )

    AppHorizontalPager(
        screens = pagerScreens,
        contentContainer = ForgotPasswordPagerContainer(
            modifier = Modifier.padding(horizontal = Dimens.ExtraLargePadding)
        ),
        userScrollEnabled = false,
        titleContainer = {
            PagerProgressIndicator(
                currentPage = it.getCurrentPage(),
                pageCount = it.pageCount,
                onBackButtonClick = it::requestPrevious,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .align(Alignment.Start)
            )
        },
        bottomContainer = {
            ForgotPasswordPagerLoadingButton(
                pagerTitleResId = pagerScreens[it.getCurrentPage()].titleResId,
                onClick = it::requestNext
            )

            MediumVerticalSpacer()

            if(pagerScreens[it.getCurrentPage()].titleResId == R.string.forgotPassword_enter_digits_code){
                DidntReceivedCodeText(
                    onResendClick = {}
                )
            }

            MediumVerticalSpacer()
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ForgotPasswordPagerLoadingButton(
    pagerTitleResId: Int,
    onClick: () -> Unit = {}
) {
    val buttonText = stringResource(
        when (pagerTitleResId) {
            R.string.forgotPassword_forgot_password -> R.string.forgotPassword_send
            R.string.forgotPassword_enter_digits_code -> R.string.forgotPassword_verify
            R.string.forgotPassword_reset_password -> R.string.forgotPassword_reset_password
            else -> return
        }
    )

    AppElevatedLoadingButton(
        text = buttonText,
        isLoading = false,
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.9f)
    )
}

@Composable
private fun DidntReceivedCodeText(
    onResendClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.forgotPassword_didnt_receive_code))
        Text(
            text = stringResource(R.string.forgotPassword_resend),
            modifier = Modifier.clickable(onClick = onResendClick)
        )
    }
}


@Composable
private fun PagerProgressIndicator(
    currentPage: Int,
    pageCount: Int,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            isEnabled = currentPage != 0,
            onClick = onBackButtonClick
        )
        AppAnimatedLinearProgressIndicator(
            progress = (currentPage.toFloat() / pageCount.toFloat()),
            modifier = Modifier.fillMaxWidth()
        )
    }
}