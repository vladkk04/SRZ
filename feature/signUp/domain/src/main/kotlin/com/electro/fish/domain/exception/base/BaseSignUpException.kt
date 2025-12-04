package com.electro.fish.domain.exception.base

import com.electro.fish.domain.resources.SignUpStringProvider
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.exception.base.WithLocalizedMessage
import com.electro.essential.resources.StringProviderStore

abstract class BaseSignUpException(
    override val message: String = "",
    override val cause: Throwable? = null
) : BaseAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String =
        getLocalizedErrorMessage(stringProviderStore<SignUpStringProvider>())

    abstract fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String
}
