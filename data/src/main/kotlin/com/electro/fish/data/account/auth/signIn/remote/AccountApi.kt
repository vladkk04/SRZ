package com.electro.fish.data.account.auth.signIn.remote

import com.electro.fish.data.account.auth.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.account.auth.signIn.remote.dto.SignInResponseDto

internal interface AccountApi {
    suspend fun signIn(request: SignInRequestDto): SignInResponseDto
}