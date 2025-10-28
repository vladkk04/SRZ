package com.electro.fish.data.network

import kotlin.time.Duration.Companion.seconds

object NetworkConfig {
    const val BASE_URL = "http://192.168.68.58:8080/"
    const val AUTH_PATH_URL = "api/auth"
    const val IS_DEBUG: Boolean = false
    val timeout = 10.seconds
}
