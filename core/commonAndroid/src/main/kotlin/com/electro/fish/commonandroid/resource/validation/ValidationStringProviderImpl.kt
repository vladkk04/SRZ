package com.electro.fish.commonandroid.resource.validation

import android.content.Context
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

open class ValidationStringProviderImpl (
    private val context: Context,
): ValidationStringProvider {
    override fun emptyInputFieldError(field: BaseInputField.InputField): String {
        return context.getString(R.string.commonAndroid_default_empty_input_field_error, field.getFieldName(this))
    }

    override fun emptyImageFieldError(field: BaseInputField.ImageInputField<*>): String {
        return context.getString(R.string.commonAndroid_default_empty_image_field_error, field.getFieldName(this))
    }

    override fun invalidRegexInputFieldError(
        field: BaseInputField.InputField,
        customErrorMessageResolver: ((ValidationStringProvider) -> String)?
    ): String {
        return customErrorMessageResolver?.invoke(this) ?: context.getString(
            R.string.commonAndroid_default_invalid_regex_input_field_error,
            field.getFieldName(this)
        )
    }
}