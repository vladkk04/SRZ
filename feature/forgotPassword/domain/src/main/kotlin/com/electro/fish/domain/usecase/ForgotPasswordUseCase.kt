package com.electro.fish.domain.usecase

import com.electro.fish.data.account.forgotPassword.ForgotPasswordRepository
import com.electro.fish.data.account.forgotPassword.model.Email
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val forgotPasswordRepository: ForgotPasswordRepository
) {
    suspend fun invoke(email: Email) { 
        forgotPasswordRepository.forgotPassword(email)
    }
}