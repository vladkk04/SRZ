package com.electro.fish.data

import com.electro.fish.data.account.signIn.model.AuthCredential
import com.electro.fish.data.model.AuthToken

interface AccountRepository {
    suspend fun signIn(credential: AuthCredential): AuthToken
}