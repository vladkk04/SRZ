package com.electro.fish.data

import com.electro.fish.data.account.signUp.model.CreateAccountCredentials

interface CreateAccountRepository {
    suspend fun createAccount(createAccountCredentials: CreateAccountCredentials)
}