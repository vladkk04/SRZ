package com.electro.presentation.signUp.resources

import android.content.Context
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.fish.domain.resources.SignUpStringProvider
import com.electro.fish.feature.signUp.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignUpStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val authValidationStringProvider: AuthValidationStringProvider,
) : SignUpStringProvider, AuthValidationStringProvider by authValidationStringProvider {
    override val userAlreadyExistError: String = context.getString(R.string.user_already_exist_error)
}