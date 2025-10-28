package com.electro.essential.exception.base

abstract class BaseValidationException(
    override val message: String,
    override val cause: Throwable? = null
): BaseAppException(message, cause)