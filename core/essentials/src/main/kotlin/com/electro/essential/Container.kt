package com.electro.essential

import com.electro.essential.exception.base.BaseAppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class Container<out T> {
    abstract fun <R> fold(
        onSuccess: (T) -> R,
        onError: (BaseAppException) -> R,
        onLoading: () -> R
    ): R

    data object Loading : Container<Nothing>() {
        override fun <R> fold(
            onSuccess: (Nothing) -> R,
            onError: (BaseAppException) -> R,
            onLoading: () -> R
        ): R = onLoading()
    }

    data class Success<T>(val data: T) : Container<T>() {
        override fun <R> fold(
            onSuccess: (T) -> R,
            onError: (BaseAppException) -> R,
            onLoading: () -> R
        ): R = onSuccess(data)
    }

    data class Error(val exception: BaseAppException) : Container<Nothing>() {
        override fun <R> fold(
            onSuccess: (Nothing) -> R,
            onError: (BaseAppException) -> R,
            onLoading: () -> R
        ): R = onError(exception)
    }
}

fun <T, R> Container<T>.map(transform: (T) -> R): Container<R> = fold(
    onSuccess = { data -> Container.Success(transform(data)) },
    onError = { exception -> Container.Error(exception) },
    onLoading = { Container.Loading }
)

fun <T, R> Flow<Container<T>>.mapFlow(transform: (T) -> R): Flow<Container<R>> =
    map { it.map(transform) }

fun <T, R : Any> Container<T>.foldNullable(
    onSuccess: (T) -> R? = { null },
    onError: (BaseAppException) -> R? = { null },
    onLoading: () -> R? = { null }
): R? = fold(onSuccess, onError, onLoading)

fun <T> Container<T>.getExceptionOrNull(): BaseAppException? = foldNullable(onError = { it })

fun <T> Container<T>.getValueOrNull(): T? = foldNullable(onSuccess = { it })