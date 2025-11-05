package com.electro.presentation.completeAccountSetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.FocusManagerAction

@Composable
fun ProfileSetupFirstScreen(
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AppOutlinedTextField(
            label = "FirstName",
            focusRequester = focusRequester,
        )

        AppOutlinedTextField(
            label = "LastName",
            focusManagerAction = FocusManagerAction.Done()
        )
    }

}