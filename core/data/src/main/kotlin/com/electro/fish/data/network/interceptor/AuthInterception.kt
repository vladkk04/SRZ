package com.electro.fish.data.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.bearerAuth

class AuthInterceptor (
    private val authTokenProvider: AuthTokenProvider
) {
    fun interceptor(client : HttpClient) {
        client.plugin(HttpSend).intercept { request ->
            authTokenProvider.provideAuthToken()?.let { token ->
                request.bearerAuth(token)
            }
            execute(request)
        }
    }
}

interface AuthTokenProvider {
    fun provideAuthToken(): String?
}