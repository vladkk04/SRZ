package com.electro.fish.data.network.client

import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.client.converter.createDefaultJson
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import com.electro.fish.data.network.interceptor.AuthInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.Duration

internal fun createKtorClient(
    timeout: Duration,
    authInterceptionTokenProvider: AuthInterceptionTokenProvider
) = HttpClient(Android) {
    defaultRequest {
        url(NetworkConfig.BASE_URL)
        contentType(ContentType.Application.Json)
    }
    install(HttpTimeout) {
        connectTimeoutMillis = timeout.inWholeMilliseconds
        requestTimeoutMillis = timeout.inWholeMilliseconds
    }
    install(ContentNegotiation) { json(createDefaultJson()) }
    install(Logging) { level = LogLevel.ALL }
}.apply {
    AuthInterceptor(authInterceptionTokenProvider).interceptor(this)
}