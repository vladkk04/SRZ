@file:OptIn(ExperimentalSerializationApi::class)

package com.electro.fish.data.network.client.converter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal fun createDefaultJson() = Json {
    //Should remove in release
    prettyPrint = true

    explicitNulls = false
    encodeDefaults = true
    ignoreUnknownKeys = true
    isLenient = true
}