package com.electro.fish.data.account.signUp.remote

import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.model.AuthToken

internal interface CreateAccountApi {
    suspend fun signUp(request: SignUpRequestDto): AuthToken
}