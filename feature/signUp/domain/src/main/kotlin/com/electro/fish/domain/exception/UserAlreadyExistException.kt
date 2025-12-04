package com.electro.fish.domain.exception

import com.electro.fish.domain.exception.base.BaseSignUpException
import com.electro.fish.domain.resources.SignUpStringProvider

class UserAlreadyExistException: BaseSignUpException() {
    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return stringProvider.userAlreadyExistError
    }
}