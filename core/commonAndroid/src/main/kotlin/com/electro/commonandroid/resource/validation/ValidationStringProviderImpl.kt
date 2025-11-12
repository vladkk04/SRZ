package com.electro.commonandroid.resource.validation

import android.content.Context
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

class ValidationStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ValidationStringProvider {
    override fun emptyInputFieldError(field: BaseInputField.TextInputField): String =
        context.getString(
            R.string.commonAndroid_default_empty_input_field_error,
            field.fieldName(this)
        )

    override fun invalidRegexInputFieldError(field: BaseInputField.TextInputField): String =
        field.regexMessage?.let { it(this) } ?: context.getString(
            R.string.commonAndroid_default_invalid_regex_input_field_error,
            field.fieldName(this)
        )
}