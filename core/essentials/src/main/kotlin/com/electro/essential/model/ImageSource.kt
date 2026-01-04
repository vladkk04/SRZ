package com.electro.essential.model

sealed interface ImageSource {

    data object Empty: ImageSource

    data class Local(val drawableResId: Int): ImageSource

    data class Remote(
        val url: String
    ): ImageSource
}