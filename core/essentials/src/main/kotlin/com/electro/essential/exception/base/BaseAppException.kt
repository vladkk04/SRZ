package com.electro.essential.exception.base

abstract class BaseAppException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause)





