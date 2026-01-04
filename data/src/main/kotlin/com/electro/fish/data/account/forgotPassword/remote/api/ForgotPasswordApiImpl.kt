package com.electro.fish.data.account.forgotPassword.remote.api

import com.electro.fish.data.account.forgotPassword.remote.ForgotPasswordApi
import com.electro.fish.data.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import javax.inject.Inject

class ForgotPasswordApiImpl @Inject constructor(
    private val client: HttpClient
): ForgotPasswordApi {
    override suspend fun resetPassword(email: String) {
        client.post(NetworkConfig.FORGOT_PASSWORD_PATH_URL) {
            parameter("email", email)
        }
    }
}