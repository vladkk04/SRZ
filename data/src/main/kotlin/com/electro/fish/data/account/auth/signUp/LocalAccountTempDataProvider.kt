package com.electro.fish.data.account.auth.signUp

import kotlinx.coroutines.flow.Flow

interface LocalAccountTempDataProvider {
    fun getEmailByFlow(): Flow<String>
    fun getPasswordByFlow(): Flow<String>

    suspend fun getEmail(): String
    suspend fun getPassword(): String
}