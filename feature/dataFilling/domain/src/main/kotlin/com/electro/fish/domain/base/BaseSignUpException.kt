package com.electro.fish.domain.base

import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.WithLocalizedMessage
import com.electro.essential.resources.StringProviderStore
import com.electro.fish.domain.resources.DataFillingStringProvider

abstract class BaseDataFillingException(
    override val message: String = "",
    override val cause: Throwable? = null
) : BaseAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String =
        getLocalizedErrorMessage(stringProviderStore<DataFillingStringProvider>())

    abstract fun getLocalizedErrorMessage(stringProvider: DataFillingStringProvider): String
}
