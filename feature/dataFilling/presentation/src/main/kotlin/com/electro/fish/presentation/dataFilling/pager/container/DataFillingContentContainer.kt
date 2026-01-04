package com.electro.fish.presentation.dataFilling.pager.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.electro.fish.ui.component.PagerContentContainer

class DataFillingContentContainer(
    private val modifier: Modifier = Modifier,
): PagerContentContainer<DataFillingScreenPagerFeature> {

    @Composable
    override fun Wrap(
        screenFeature: DataFillingScreenPagerFeature,
        content: @Composable () -> Unit
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(screenFeature.titleResId),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(1f)
            ) { content() }
        }
    }
}