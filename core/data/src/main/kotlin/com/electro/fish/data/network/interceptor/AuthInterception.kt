package com.electro.fish.data.network.interceptor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.plugin
import io.ktor.client.request.bearerAuth


class AuthInterceptor (
    private val authInterceptionTokenProvider: AuthInterceptionTokenProvider
) {
    fun interceptor(client : HttpClient) {
        client.plugin(HttpSend).intercept { request ->
            authInterceptionTokenProvider.provideToken()?.let(request::bearerAuth)
            execute(request)
        }
    }
}