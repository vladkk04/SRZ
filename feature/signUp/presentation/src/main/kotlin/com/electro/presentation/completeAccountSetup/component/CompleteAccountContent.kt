package com.electro.presentation.completeAccountSetup.component

import android.R.attr.fontStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CompleteAccountContent(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.Start)
        )

        content()
    }
}