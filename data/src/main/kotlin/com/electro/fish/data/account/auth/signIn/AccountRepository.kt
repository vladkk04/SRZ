package com.electro.fish.data.account.auth.signIn

import com.electro.fish.data.account.auth.signIn.model.AuthCredential
import com.electro.fish.data.account.auth.model.AuthToken

interface AccountRepository {
    suspend fun signIn(credential: AuthCredential): AuthToken
}