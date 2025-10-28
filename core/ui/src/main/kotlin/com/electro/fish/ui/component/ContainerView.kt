package com.electro.fish.ui.component

import android.util.Log.e
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.electro.essential.Container
import com.electro.essential.exception.mapper.ExceptionToMessageMapper

@Composable
fun <T> ContainerView(
    container: Container<T>,
    modifier: Modifier = Modifier,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    content: @Composable (T) -> Unit
) {
    Box(modifier = modifier) {
        container.fold(
            onLoading = {

            },
            onSuccess = {

            },
            onError = { e ->
                val message = exceptionToMessageMapper.getLocalizedMessage(e)
                e("ContainerView", message)
            }
        )
    }
}