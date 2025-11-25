package com.electro.commonandroid.resource.validation

import android.content.Context
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthValidationStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): AuthValidationStringProvider {
    override val email: String = context.getString(R.string.commonAndroid_email)
    override val password: String = context.getString(R.string.commonAndroid_password)
    override val passwordRegexError: String = context.getString(R.string.commonAndroid_password_regex_error)

    override fun emptyInputFieldError(field: BaseInputField.TextInputField): String =
        context.getString(
            R.string.commonAndroid_default_empty_input_field_error,
            field.fieldName(this)
        )

    override fun invalidRegexInputFieldError(field: BaseInputField.TextInputField): String =
        field.regexMessage(this) ?: context.getString(
            R.string.commonAndroid_default_invalid_regex_input_field_error,
            field.fieldName(this)
        )
}