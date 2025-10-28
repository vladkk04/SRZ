package com.electro.presentation.resources

import android.content.Context
import com.electro.domain.entity.InputField
import com.electro.domain.resources.SignInStringProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignInStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : SignInStringProvider {
    override val invalidCredentialsError: String
        get() = "test"

    override val invalidEmailError: String
        get() = "emial Error"

    override fun emptyFieldError(inputField: InputField): String {
       return when (inputField) {
           InputField.Email -> {"email "}
           InputField.Password -> { "paspwr"}
       }
    }

}