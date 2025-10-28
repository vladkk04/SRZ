package com.electro.domain.exceptions

import com.electro.domain.entity.InputField
import com.electro.domain.exceptions.base.BaseSignInException
import com.electro.domain.resources.SignInStringProvider

class EmptyInputFieldException(val inputField: InputField): BaseSignInException("Invalid login or password") {

    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return stringProvider.emptyFieldError(inputField)
    }

}