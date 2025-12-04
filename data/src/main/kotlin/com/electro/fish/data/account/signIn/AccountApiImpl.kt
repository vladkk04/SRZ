package com.electro.fish.data.account.signIn

import com.electro.fish.data.account.signIn.remote.AccountApi
import com.electro.fish.data.account.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.account.signIn.remote.dto.SignInResponseDto
import com.electro.fish.data.model.AuthToken
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.utils.safeResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.basicAuth
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class AccountApiImpl @Inject constructor(
    private val client: HttpClient
): AccountApi {
    override suspend fun signIn(request: SignInRequestDto): SignInResponseDto {
        val response = client.post(NetworkConfig.SIGN_IN_PATH_URL) {
            basicAuth(request.email, request.password)
        }

        return safeResponse(response) { SignInResponseDto(AuthToken(response.bodyAsText())) }
    }
}