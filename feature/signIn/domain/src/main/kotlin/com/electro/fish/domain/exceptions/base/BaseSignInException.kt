package com.electro.fish.domain.exceptions.base

import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.essential.exception.base.BaseAppException
import com.electro.essential.resources.StringProviderStore
import com.electro.essential.exception.base.WithLocalizedMessage

abstract class BaseSignInException(
    override val message: String = "",
    override val cause: Throwable? = null
) : BaseAppException(message, cause), WithLocalizedMessage {
    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String =
        getLocalizedErrorMessage(stringProviderStore<SignInStringProvider>())

    abstract fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String
}