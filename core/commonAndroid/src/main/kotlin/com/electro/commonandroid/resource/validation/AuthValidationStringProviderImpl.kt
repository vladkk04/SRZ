package com.electro.commonandroid.resource.validation

import android.content.Context
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.ValidationStringProvider
import com.electro.essential.validator.BaseInputField
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthValidationStringProviderImpl @Inject constructor(
    private val validationStringProvider: ValidationStringProvider,
    @param:ApplicationContext private val context: Context
): AuthValidationStringProvider, ValidationStringProvider by validationStringProvider {
    override val email: String = context.getString(R.string.commonAndroid_email)
    override val password: String = context.getString(R.string.commonAndroid_password)
    override val passwordRegexError: String = context.getString(R.string.commonAndroid_password_regex_error)
}