package com.electro.fish.domain.exception

import com.electro.fish.domain.base.BaseDataFillingException
import com.electro.fish.domain.resources.DataFillingStringProvider

class UserAlreadyExistException: BaseDataFillingException() {
    override fun getLocalizedErrorMessage(stringProvider: DataFillingStringProvider): String {
        return "need change user already exist"
    }
}