package com.electro.presentation.completeAccountSetup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CompleteAccountContent(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
           text = title
        )

        content()
    }

}