package com.electro.fish.data.account.signUp.remote

import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto

internal interface CreateAccountApi {
    suspend fun signUp(request: SignUpRequestDto)
}