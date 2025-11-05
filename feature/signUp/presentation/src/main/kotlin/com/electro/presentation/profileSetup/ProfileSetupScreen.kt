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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.theme.Dimens
import com.electro.presentation.profileSetup.component.ProfileSetupContentContainer

@Composable
fun ProfileSetupScreen() {
    val viewModel = hiltViewModel<CompleteAccountSetupViewModel>()


    ProfileSetupContent()
}

@Composable
private fun ProfileSetupContent() {
    val pagerState = rememberPagerState { 3 }

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
            beyondViewportPageCount = 1,
            modifier = Modifier.weight(0.95f)
        ) { index ->
            when (index) {
                0 -> {
                    ProfileSetupContentContainer(
                        "About your self"
                    ) { ProfileSetupFirstScreen() }
                }

                1 -> {
                    ProfileSetupContentContainer(
                        "Your birthday"
                    ) { ProfileSetupSecondScreen() }
                }
            }
        }

        AppElevatedButton(
            text = stringResource(R.string.signUp_continue),
            onClick = { pagerState.requestScrollToPage(1) },
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
        AppIconButton(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = {
                pagerState.requestScrollToPage(
                    page = (pagerState.currentPage - 1).coerceAtLeast(0)
                )
            },
            isEnabled = pagerState.currentPage != 0
        )
        AppAnimatedLinearProgressIndicator(
            progress = (pagerState.currentPage.toFloat() / pagerState.pageCount.toFloat()),
            modifier = Modifier.fillMaxWidth()
        )
    }
}