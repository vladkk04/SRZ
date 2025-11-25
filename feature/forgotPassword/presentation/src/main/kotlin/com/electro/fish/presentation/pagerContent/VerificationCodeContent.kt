package com.electro.fish.presentation.pagerContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import com.electro.fish.ui.component.AppOutlinedOtpTextField

@Composable
fun VerificationCodeContent(
    onCodeValueChange: (Int) -> Unit,
    focusRequester: FocusRequester,
    onNextImeAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppOutlinedOtpTextField(
        modifier = modifier
    )
}

