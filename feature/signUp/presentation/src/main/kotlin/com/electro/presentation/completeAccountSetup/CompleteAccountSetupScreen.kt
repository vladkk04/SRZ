package com.electro.presentation.completeAccountSetup

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.signUp.presentation.R
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.theme.Dimens
import com.electro.presentation.completeAccountSetup.component.CompleteAccountContent

@Composable
fun CompleteAccountSetupScreen() {
    val viewModel = hiltViewModel<CompleteAccountSetupViewModel>()


    CompleteAccountSetupContent()
}

@Composable
private fun CompleteAccountSetupContent() {
    val pagerState = rememberPagerState { 3 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.05f)
        ) {
            if (pagerState.currentPage > 0) {
                AppIconButton(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    onClick = { pagerState.requestScrollToPage(0) }
                )
            }
            AppAnimatedLinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier
                .weight(0.95f)
        ) { index ->
            when(index) {
                0 -> {
                    CompleteAccountContent(
                        "About your self"
                    ) { ProfileSetupFirstScreen() }
                }

                1 -> {
                    CompleteAccountContent(
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
