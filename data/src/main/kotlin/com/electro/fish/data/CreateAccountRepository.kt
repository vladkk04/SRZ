package com.electro.fish.data

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.model.AuthToken

interface CreateAccountRepository {
    suspend fun createAccount(createAccountCredentials: CreateAccountCredentials): AuthToken
}