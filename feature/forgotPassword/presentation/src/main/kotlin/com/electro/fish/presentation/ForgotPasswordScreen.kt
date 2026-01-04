@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.presentation

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.forgotPassword.presentation.R
import com.electro.fish.presentation.container.ForgotPasswordPagerContainer
import com.electro.fish.presentation.container.ForgotPasswordPagerScreenFeature
import com.electro.fish.presentation.pagerContent.ConfirmationToSentEmailContent
import com.electro.fish.presentation.pagerContent.EmailSendContent
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppHorizontalPager
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
            titleResId = R.string.forgotPassword_link_sent_email,
            descriptionResId = R.string.forgotPassword_check_your_email,
            content = { ConfirmationToSentEmailContent() }
        )
    )

    AppHorizontalPager(
        screens = pagerScreens,
        contentContainer = ForgotPasswordPagerContainer(
            modifier = Modifier.padding(horizontal = Dimens.ExtraLargePadding)
        ),
        fillMaxSize = false,
        userScrollEnabled = false,
        bottomContainer = {
            MediumVerticalSpacer()

            if(it.getCurrentPage() == it.pageCount - 1) {
                OpenEmailButton(
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
            }

            MediumVerticalSpacer()

            ForgotPasswordPagerLoadingButton(
                pagerTitleResId = pagerScreens[it.getCurrentPage()].titleResId,
                onClick = {
                    if(it.getCurrentPage() != it.pageCount - 1) {
                        viewModel.test()
                        it.requestNext()
                    } else {
                        viewModel.navigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            MediumVerticalSpacer()
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ForgotPasswordPagerLoadingButton(
    pagerTitleResId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val buttonText = stringResource(
        when (pagerTitleResId) {
            R.string.forgotPassword_forgot_password -> R.string.forgotPassword_send
            else -> R.string.forgotPassword_ok
        }
    )

    AppElevatedLoadingButton(
        text = buttonText,
        isLoading = false,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
private fun OpenEmailButton(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AppElevatedButton(
        text = stringResource(R.string.forgotPassword_open_email),
        onClick = {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_APP_EMAIL)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)

        },
        modifier = modifier
    )
}
