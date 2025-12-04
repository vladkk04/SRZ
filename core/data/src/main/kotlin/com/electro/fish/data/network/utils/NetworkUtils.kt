package com.electro.fish.data.network.utils

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.electro.essential.exception.HttpException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend fun <T> safeResponse(
    httpResponse: HttpResponse,
    block: suspend () -> T
): T {
    if(!httpResponse.status.isSuccess()) throw HttpException(httpResponse.status.value)
    return block()
}