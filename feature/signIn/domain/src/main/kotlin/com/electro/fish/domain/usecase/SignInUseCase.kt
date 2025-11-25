package com.electro.fish.domain.usecase

import android.util.Log
import com.electro.essential.validator.ValidationResult
import com.electro.fish.data.AccountRepository
import com.electro.fish.data.LocalAuthTokenRepository
import com.electro.fish.domain.model.SignInCredentials
import com.electro.fish.domain.model.mapToAuthCredential
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val localAuthTokenRepository: LocalAuthTokenRepository,
    private val accountRepository: AccountRepository,
    private val signInValidator: SignInValidator,
) {
    suspend operator fun invoke(credential: SignInCredentials) {
        when (val result = signInValidator.validate(credential)) {
            is ValidationResult.Error -> throw result.exceptions.first()
            ValidationResult.Success -> {
                val token = accountRepository.signIn(credential.mapToAuthCredential())
                Log.d("debug", token.getRole().toString())
                localAuthTokenRepository.saveToken(token.token)
            }
        }
    }
}

