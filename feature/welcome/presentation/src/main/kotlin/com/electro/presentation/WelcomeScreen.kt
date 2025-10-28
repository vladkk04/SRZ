package com.electro.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.electro.fish.feature.welcome.presentation.R
import com.electro.fish.ui.component.AppButtonText
import com.electro.fish.ui.component.AppElevatedButton
import com.electro.fish.ui.component.LogoCircle
import com.electro.fish.ui.theme.Dimens

@Composable
fun WelcomeScreen() {
    val viewModel = hiltViewModel<WelcomeViewModel>()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.weight(0.4f))

        LogoCircle(modifier = Modifier.size(192.dp))

        Spacer(Modifier.weight(1f))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.XLargePadding),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            AppElevatedButton(
                text = stringResource(R.string.welcome_sign_in),
                onClick = viewModel::launchSignInScreen,
                modifier = Modifier.fillMaxWidth()
            )

            AppElevatedButton(
                text = stringResource(R.string.welcome_sign_up),
                onClick = viewModel::launchSignUpScreen,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.weight(0.1f))
    }

}