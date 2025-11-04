package com.electro.essential.exception.base

import com.electro.essential.resources.StringProviderStore
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField

abstract class BaseValidationException (
    override val message: String,
    override val cause: Throwable? = null
): BaseAppException(message, cause), WithLocalizedMessage {

    abstract val inputField: BaseInputField<*>

    override fun getLocalizedErrorMessage(stringProviderStore: StringProviderStore): String =
        getLocalizedErrorMessage(stringProviderStore<ValidationStringProvider>())

    abstract fun getLocalizedErrorMessage(stringProvider: ValidationStringProvider): String
}