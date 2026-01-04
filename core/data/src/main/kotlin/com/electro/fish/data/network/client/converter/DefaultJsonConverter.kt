package com.electro.fish.data.network.client.converter

import kotlinx.serialization.json.Json

internal fun createDefaultJson() = Json {
    explicitNulls = false
    encodeDefaults = true
    isLenient = true
}