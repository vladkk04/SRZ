package com.electro.fish.data.account.auth.signUp.repository

import com.electro.fish.data.account.auth.signUp.CreateAccountRepository
import com.electro.fish.data.account.auth.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.auth.signUp.model.mapToRequestDto
import com.electro.fish.data.account.auth.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.auth.model.AuthToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateAccountRepositoryImpl @Inject constructor(
    private val api: CreateAccountApi
): CreateAccountRepository {
    override suspend fun createAccount(createAccountCredentials: CreateAccountCredentials): AuthToken =
        api.signUp(createAccountCredentials.mapToRequestDto()).token
}