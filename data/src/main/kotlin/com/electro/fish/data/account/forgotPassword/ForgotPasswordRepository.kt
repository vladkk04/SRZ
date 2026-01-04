package com.electro.fish.data.account.forgotPassword

import com.electro.fish.data.account.forgotPassword.model.Email

interface ForgotPasswordRepository {
    suspend fun forgotPassword(email: Email)
}