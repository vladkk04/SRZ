package com.electro.fish.data.account.auth.signIn.repository

import com.electro.fish.data.account.auth.signIn.AccountRepository
import com.electro.fish.data.account.auth.signIn.model.AuthCredential
import com.electro.fish.data.account.auth.signIn.model.mapToRequestDto
import com.electro.fish.data.account.auth.signIn.remote.AccountApi
import com.electro.fish.data.account.auth.model.AuthToken
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
    private val api: AccountApi
): AccountRepository {
    override suspend fun signIn(credential: AuthCredential): AuthToken =
        api.signIn(credential.mapToRequestDto()).token
}