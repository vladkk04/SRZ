package com.electro.fish.data.account.auth.signUp

import com.electro.fish.data.account.auth.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.auth.model.AuthToken

interface CreateAccountRepository {
    suspend fun createAccount(createAccountCredentials: CreateAccountCredentials): AuthToken
}