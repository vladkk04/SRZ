package com.electro.fish.domain.usecase

import com.electro.fish.data.account.auth.token.AuthTokenSaver
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authTokenSaver: AuthTokenSaver
) {
    suspend operator fun invoke() {
        authTokenSaver.saveToken(null)
    }
}