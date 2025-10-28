package com.electro.essential.handler

fun interface ExceptionHandler {
    fun handleException(exception: Exception)
}