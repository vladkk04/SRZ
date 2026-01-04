package com.electro.fish.presentation.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.electro.fish.ui.component.MediumVerticalSpacer
import com.electro.fish.ui.component.PagerContentContainer

class ForgotPasswordPagerContainer(
    private val modifier: Modifier = Modifier
) : PagerContentContainer<ForgotPasswordPagerScreenFeature> {

    @Composable
    override fun Wrap(
        screenFeature: ForgotPasswordPagerScreenFeature,
        content: @Composable () -> Unit
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = stringResource(screenFeature.titleResId),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.Start)
            )

            MediumVerticalSpacer()

            Text(
                text = stringResource(screenFeature.descriptionResId),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.Start)
            )

            MediumVerticalSpacer()

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) { content() }
        }
    }
}