package com.electro.fish.presentation.resources

import android.content.Context
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.fish.feature.signIn.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val authValidationStringProvider: AuthValidationStringProvider,
) : SignInStringProvider, AuthValidationStringProvider by authValidationStringProvider {
    override val invalidCredentialsError: String = context.getString(R.string.signIn_invalid_credentials_error)
    override val emailCannotBeFoundInSystemError: String = context.getString(R.string.signIn_email_cannot_be_found_in_system_error)
}