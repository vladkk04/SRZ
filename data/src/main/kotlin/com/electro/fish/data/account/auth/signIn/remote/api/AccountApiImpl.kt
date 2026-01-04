package com.electro.fish.data.account.auth.signIn.remote.api

import com.electro.fish.data.account.auth.signIn.remote.AccountApi
import com.electro.fish.data.account.auth.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.account.auth.signIn.remote.dto.SignInResponseDto
import com.electro.fish.data.account.auth.model.AuthToken
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.utils.map
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class AccountApiImpl @Inject constructor(
    private val client: HttpClient
) : AccountApi {
    override suspend fun signIn(request: SignInRequestDto): SignInResponseDto = client
        .post(NetworkConfig.SIGN_IN_PATH_URL) { setBody(request) }
        .map { SignInResponseDto(AuthToken(it.bodyAsText())) }
}