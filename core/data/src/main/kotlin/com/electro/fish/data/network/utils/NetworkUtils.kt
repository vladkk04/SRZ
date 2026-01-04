package com.electro.fish.data.network.utils

import com.electro.essential.exception.HttpException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess


suspend fun <T> HttpResponse.map(
    block: suspend (HttpResponse) -> T
): T = block(this)

suspend fun <T> HttpResponse.safeResponse(
    block: suspend (HttpResponse) -> T
): T {
    if(!this.status.isSuccess()) throw HttpException(this.status.value, this.status.description)
    return block(this)
}