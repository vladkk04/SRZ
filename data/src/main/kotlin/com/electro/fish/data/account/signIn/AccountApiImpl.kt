package com.electro.fish.data.account.signIn

import android.util.Log
import com.electro.fish.data.account.signIn.remote.AccountApi
import com.electro.fish.data.account.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.account.signIn.remote.dto.SignInResponseDto
import com.electro.fish.data.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.basicAuth
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class AccountApiImpl @Inject constructor(
    private val client: HttpClient
): AccountApi {
    override suspend fun signIn(request: SignInRequestDto): SignInResponseDto {
        val token = client.post(NetworkConfig.SIGN_IN_PATH_URL) {
            basicAuth(request.email, request.password)
        }.bodyAsText()

        Log.d("debug", "AuthToken sign in: $token")

        return SignInResponseDto(token)
    }
}