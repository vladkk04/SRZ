package com.electro.fish.presentation.resources

import android.content.Context
import androidx.compose.material3.SearchBarDefaults.InputField
import com.electro.fish.domain.resources.SignInStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : SignInStringProvider {
    override val invalidCredentialsError: String = "Invalid Credentials"
    override val emailNotRegisteredError: String = "Email not registered"
}