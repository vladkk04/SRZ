package com.electro.fish.data.account.forgotPassword.remote

interface ForgotPasswordApi {
    suspend fun resetPassword(email: String)
}