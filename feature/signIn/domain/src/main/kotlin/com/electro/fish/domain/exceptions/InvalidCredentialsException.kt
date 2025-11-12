package com.electro.fish.domain.exceptions

import com.electro.fish.domain.exceptions.base.BaseSignInException
import com.electro.fish.domain.resources.SignInStringProvider

class InvalidCredentialsException: BaseSignInException("Invalid credentials") {
    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return "stringProvider.invalidCredentialsError"
    }
}