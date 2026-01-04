package com.electro.fish.data.account.forgotPassword.repository

import com.electro.fish.data.account.forgotPassword.ForgotPasswordRepository
import com.electro.fish.data.account.forgotPassword.remote.ForgotPasswordApi
import com.electro.fish.data.account.forgotPassword.model.Email
import javax.inject.Inject

class ForgotPasswordRepositoryImpl @Inject constructor(
    private val forgotPasswordApi: ForgotPasswordApi
) : ForgotPasswordRepository {
    override suspend fun forgotPassword(email: Email) { forgotPasswordApi.resetPassword(email.value) }
}