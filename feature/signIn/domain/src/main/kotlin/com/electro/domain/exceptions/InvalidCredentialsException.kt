package com.electro.domain.exceptions

import com.electro.domain.exceptions.base.BaseSignInException
import com.electro.domain.resources.SignInStringProvider

class InvalidCredentialsException: BaseSignInException("Invalid credentials") {
    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return stringProvider.invalidCredentialsError
    }
}