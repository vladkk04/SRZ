package com.electro.presentation.profileSetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.size.Dimension
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.theme.Dimens
import com.electro.presentation.profileSetup.component.ProfileSetupContentContainer
import com.electro.presentation.profileSetup.viewpagerScreen.ProfileSetupBirthdayScreen
import com.electro.presentation.profileSetup.viewpagerScreen.ProfileSetupLicenceScreen
import com.electro.presentation.profileSetup.viewpagerScreen.ProfileSetupFullNameScreen

val profileSetupScreens: List<Pair<Int, @Composable () -> Unit>> = listOf(
    R.string.signUp_title_profile_setup_whats_your_name to { ProfileSetupFullNameScreen() },
    R.string.signUp_title_profile_setup_whats_your_birthday to { ProfileSetupBirthdayScreen() },
    R.string.signUp_title_profile_setup_upload_your_licence to { ProfileSetupLicenceScreen() }
)

@Composable
fun ProfileSetupScreen() {
    val viewModel = hiltViewModel<CompleteAccountSetupViewModel>()

    ProfileSetupContent()
}

@Composable
private fun ProfileSetupContent() {
    val pagerState = rememberPagerState { profileSetupScreens.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding)
    ) {
        PagerProgressBar(
            pagerState = pagerState,
            modifier = Modifier.weight(0.05f)
        )

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondViewportPageCount = profileSetupScreens.size,
            modifier = Modifier
                .padding(Dimens.LargePadding)
                .weight(0.95f)
        ) { index ->
            val (titleRes, screen) = profileSetupScreens[index]

            ProfileSetupContentContainer(
                title = stringResource(titleRes),
                content = screen,
                modifier = Modifier
            )
        }

        AppElevatedButton(
            text = stringResource(
                if (pagerState.currentPage == profileSetupScreens.size - 1) R.string.signUp_finish
                else R.string.signUp_continue
            ),
            onClick = {
                if (pagerState.currentPage < profileSetupScreens.lastIndex) {
                    pagerState.requestScrollToPage(pagerState.currentPage + 1)
                } else {

                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun PagerProgressBar(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        //isEnabled = pagerState.currentPage != 0, must be like that, or gonna crash because pagerState.currentPage - 1
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            isEnabled = pagerState.currentPage != 0,
            onClick = { pagerState.requestScrollToPage(pagerState.currentPage - 1) }
        )
        AppAnimatedLinearProgressIndicator(
            progress = (pagerState.currentPage.toFloat() / pagerState.pageCount.toFloat()),
            modifier = Modifier.fillMaxWidth()
        )
    }
}