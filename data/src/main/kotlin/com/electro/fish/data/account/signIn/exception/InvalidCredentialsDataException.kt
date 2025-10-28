package com.electro.fish.data.account.signIn.exception

import com.electro.essential.exception.base.BaseAppException

class InvalidCredentialsDataException(
    override val cause: Throwable?
): BaseAppException("Invalid login or password", cause)