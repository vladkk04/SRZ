package com.electro.essential.exception

import com.electro.essential.exception.base.BaseCoreException
import com.electro.essential.resources.CoreStringProvider

class ServerException(
    val code: Int = 400,
    override val message: String = "Server error",
    cause: Throwable? = null
) : BaseCoreException("Server error", cause) {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String =
        stringProvider.serverErrorMessage(code, message)
}