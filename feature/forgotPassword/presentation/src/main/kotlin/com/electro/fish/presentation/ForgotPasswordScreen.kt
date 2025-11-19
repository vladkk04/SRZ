@file:OptIn(ExperimentalMaterial3Api::class)

package com.electro.fish.presentation

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.presentation.component.ForgotPasswordPagerContainer
import com.electro.fish.ui.component.AppAnimatedLinearProgressIndicator
import com.electro.fish.ui.component.AppElevatedLoadingButton
import com.electro.fish.ui.component.AppIconButton
import com.electro.fish.ui.theme.Dimens

@Composable
fun ForgotPasswordScreen() {
    val viewModel = hiltViewModel<ForgotPasswordViewModel>()

    val sheetState = rememberModalBottomSheetState()
    val pagerState = rememberPagerState { 3 }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {}
    ) {
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
                beyondViewportPageCount = 3,
                modifier = Modifier
                    .padding(Dimens.LargePadding)
                    .weight(0.95f)
            ) { index ->
                ForgotPasswordPagerContainer(
                    title = "Test",
                    description = "Test",
                    content = {

                    },
                    modifier = Modifier
                )
            }

            AppElevatedLoadingButton(
                text = "Continue",
                isLoading = false,
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
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