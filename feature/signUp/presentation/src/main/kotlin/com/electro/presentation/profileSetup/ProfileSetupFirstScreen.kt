package com.electro.presentation.profileSetup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import com.electro.fish.ui.component.AppOutlinedPasswordTextField
import com.electro.fish.ui.component.AppOutlinedTextField
import com.electro.fish.ui.component.FocusManagerAction

@Composable
fun ProfileSetupFirstScreen(
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    var firstNameText by rememberSaveable { mutableStateOf("") }
    var lastNameText by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AppOutlinedTextField(
            onValueChange = { firstNameText = it },
            label = "First",
           /* errorMessage = state.emailInputFieldErrorMessage,
            isError = state.emailInputFieldErrorMessage != null,*/
            focusRequester = focusRequester,
            focusManagerAction = FocusManagerAction.Next,
            modifier = Modifier
                .fillMaxWidth()
        )

        AppOutlinedTextField(
            onValueChange = { lastNameText = it },
            label = "fds",
           /* errorMessage = state.passwordInputFieldErrorMessage,
            isError = state.passwordInputFieldErrorMessage != null,*/
            focusManagerAction = FocusManagerAction.Done {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }

}