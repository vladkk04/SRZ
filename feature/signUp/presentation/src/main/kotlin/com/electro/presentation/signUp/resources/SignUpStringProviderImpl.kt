package com.electro.presentation.signUp.resources

import android.content.Context
import com.electro.domain.resources.SignUpStringProvider
import com.electro.fish.feature.signUp.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignUpStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : SignUpStringProvider {
    override val emailLabel: String = context.getString(R.string.signUp_email)
    override val passwordLabel: String = context.getString(R.string.signUp_password)
    override val repeatPasswordLabel: String = context.getString(R.string.signUp_repeat_password)
}