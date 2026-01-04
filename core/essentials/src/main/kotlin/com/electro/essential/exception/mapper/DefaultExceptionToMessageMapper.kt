package com.electro.essential.exception.mapper

import com.electro.essential.resources.StringProviderStore
import com.electro.essential.exception.base.WithLocalizedMessage
import com.electro.essential.resources.CoreStringProvider
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    private val stringProviderStore: StringProviderStore
): ExceptionToMessageMapper {
    override fun getLocalizedMessage(exception: Exception): String {
        return if (exception is WithLocalizedMessage) {
            exception.getLocalizedErrorMessage(stringProviderStore)
        } else {
            "what"
        }
    }
}