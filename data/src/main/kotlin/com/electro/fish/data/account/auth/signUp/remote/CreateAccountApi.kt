package com.electro.fish.data.account.auth.signUp.remote

import com.electro.fish.data.account.auth.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.account.auth.signUp.remote.dto.SignUpResponseDto

internal interface CreateAccountApi {
    suspend fun signUp(request: SignUpRequestDto): SignUpResponseDto
}