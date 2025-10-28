package com.electro.domain.resources

import com.electro.essential.resources.StringProvider

interface SignUpStringProvider: StringProvider {
    val emailLabel: String
    val passwordLabel: String
    val repeatPasswordLabel: String
    //fun emptyFieldError(inputField: InputField): String

}