package com.electro.domain.usecase

import android.util.Log
import android.util.Patterns
import com.electro.domain.entity.Credentials
import com.electro.domain.entity.InputField
import com.electro.domain.entity.mapToAuthCredential
import com.electro.domain.exceptions.EmailInvalidException
import com.electro.domain.exceptions.EmptyInputFieldException
import com.electro.fish.data.AccountRepository
import com.electro.fish.data.LocalTokenRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val localTokenRepository: LocalTokenRepository
) {
    suspend operator fun invoke(credential: Credentials) {
        credential.validate()
        val token = accountRepository.signIn(credential.mapToAuthCredential())
        localTokenRepository.saveToken(token)
    }
}

internal fun Credentials.validate() {
    if (email.isBlank()) {
        throw EmptyInputFieldException(InputField.Email)
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        throw EmailInvalidException()
    } else if (password.isBlank()) {
        throw EmptyInputFieldException(InputField.Password)
    }
}
