package com.electro.fish.data.account.signUp

import com.electro.fish.data.account.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.model.AuthToken
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.utils.safeResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class CreateAccountApiImpl @Inject constructor(
    private val client: HttpClient,
): CreateAccountApi {
    override suspend fun signUp(request: SignUpRequestDto): AuthToken {
        val response = client.post(NetworkConfig.SIGN_UP_PATH_URL) { setBody(request) }

        return safeResponse(response) { AuthToken(response.bodyAsText()) }
    }
}