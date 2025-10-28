package com.electro.essential.exception

import com.electro.essential.exception.base.BaseCoreException
import com.electro.essential.resources.CoreStringProvider

class NetworkException(
    cause: Throwable? = null
) : BaseCoreException("Network error", cause) {
    override fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String =
        stringProvider.connectionErrorMessage
}