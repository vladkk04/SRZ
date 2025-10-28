package com.electro.domain.resources

import com.electro.domain.entity.InputField
import com.electro.essential.resources.StringProvider

interface SignInStringProvider: StringProvider {
    val invalidCredentialsError: String
    val invalidEmailError: String

    fun emptyFieldError(inputField: InputField): String

}