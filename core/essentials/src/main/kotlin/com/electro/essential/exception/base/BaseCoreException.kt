package com.electro.essential.exception.base

import com.electro.essential.resources.StringProviderStore
import com.electro.essential.resources.CoreStringProvider

abstract class BaseCoreException(
    override val message: String,
    override val cause: Throwable? = null
) : BaseAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String =
        getLocalizedErrorMessage(stringProviderStore<CoreStringProvider>())

    abstract fun getLocalizedErrorMessage(stringProvider: CoreStringProvider): String
}