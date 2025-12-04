package com.electro.fish.domain.resources

import com.electro.essential.resources.AuthValidationStringProvider

interface SignInStringProvider: AuthValidationStringProvider {
    val invalidCredentialsError: String
    val emailCannotBeFoundInSystemError: String
}