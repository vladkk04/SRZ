package com.electro.fish.domain.exceptions

import com.electro.fish.domain.exceptions.base.BaseSignInException
import com.electro.fish.domain.resources.SignInStringProvider

class EmailNotFoundInSystemException: BaseSignInException() {
    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return stringProvider.emailCannotBeFoundInSystemError
    }
}
