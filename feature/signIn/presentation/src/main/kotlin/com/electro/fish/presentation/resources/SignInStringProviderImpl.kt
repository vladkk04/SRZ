package com.electro.fish.presentation.resources

import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.fish.domain.resources.SignInStringProvider
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    private val authValidationStringProvider: AuthValidationStringProvider,
) : SignInStringProvider, AuthValidationStringProvider by authValidationStringProvider