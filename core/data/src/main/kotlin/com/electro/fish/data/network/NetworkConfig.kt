package com.electro.fish.data.network

import kotlin.time.Duration.Companion.seconds

object NetworkConfig {
    const val BASE_URL = "http://192.168.68.58:8080/"

    const val AUTH_PATH_URL = "api/auth"

    const val SIGN_UP_PATH_URL = "$AUTH_PATH_URL/register"
    const val SIGN_IN_PATH_URL = "$AUTH_PATH_URL/login"

    val timeout = 10.seconds
}
