package com.electro.fish.data.network.client

import android.util.Log
import com.electro.essential.exception.HttpException
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.client.converter.createDefaultJson
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import com.electro.fish.data.network.interceptor.AuthInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlin.time.Duration

internal fun createKtorClient(
    timeout: Duration,
    authInterceptionTokenProvider: AuthInterceptionTokenProvider
) = HttpClient(Android) {
    expectSuccess = true
    defaultRequest {
        url(NetworkConfig.BASE_URL)
        contentType(ContentType.Application.Json)
    }
    install(ContentNegotiation) { json(createDefaultJson()) }
    install(HttpTimeout) {
        connectTimeoutMillis = timeout.inWholeMilliseconds
        requestTimeoutMillis = timeout.inWholeMilliseconds
    }
    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("ktor", message)
            }
        }
    }
    HttpResponseValidator {
        handleResponseException { exception, _ ->
            val responseException = exception as? ResponseException ?: return@handleResponseException
            throw HttpException(responseException.response.status.value, responseException.response.status.description)
        }
    }
}.apply { AuthInterceptor(authInterceptionTokenProvider)(this) }