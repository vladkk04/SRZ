package com.electro.fish.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.electro.essential.base.Container
import com.electro.essential.exception.mapper.ExceptionToMessageMapper

@Composable
fun <T> ContainerView(
    container: Container<T>,
    modifier: Modifier = Modifier,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    content: @Composable (T) -> Unit
) {
    Box(modifier) {
        when (container) {
            is Container.Loading -> {

            }
            is Container.Error -> {

            }
            is Container.Success -> content(container.data)
        }
    }
}