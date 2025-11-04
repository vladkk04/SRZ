package com.electro.fish.domain.resources

import com.electro.essential.resources.StringProvider

interface SignInStringProvider: StringProvider {
    val invalidCredentialsError: String
    val emailNotRegisteredError: String
}