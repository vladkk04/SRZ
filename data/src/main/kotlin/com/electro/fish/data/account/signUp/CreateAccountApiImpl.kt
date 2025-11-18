package com.electro.fish.data.account.signUp

import com.electro.fish.data.account.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class CreateAccountApiImpl @Inject constructor(
    private val client: HttpClient,
): CreateAccountApi {
    override suspend fun signUp(request: SignUpRequestDto) {
        client.post(NetworkConfig.SIGN_UP_PATH_URL) {

        }
    }
}