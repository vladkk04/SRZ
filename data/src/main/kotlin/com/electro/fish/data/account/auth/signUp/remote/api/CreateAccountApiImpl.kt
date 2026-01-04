package com.electro.fish.data.account.auth.signUp.remote.api

import com.electro.fish.data.account.auth.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.auth.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.account.auth.signUp.remote.dto.SignUpResponseDto
import com.electro.fish.data.account.auth.model.AuthToken
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.utils.map
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class CreateAccountApiImpl @Inject constructor(
    private val client: HttpClient,
) : CreateAccountApi {
    override suspend fun signUp(request: SignUpRequestDto): SignUpResponseDto = client
        .post(NetworkConfig.SIGN_UP_PATH_URL) { setBody(request) }
        .map { SignUpResponseDto(AuthToken(it.bodyAsText())) }
}