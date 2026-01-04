package com.electro.fish.data.account.auth.signUp.local

import com.electro.fish.data.datastore.AppDataStore
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataProvider
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataSaver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalAccountTempData @Inject constructor(
    private val dataStore: AppDataStore
): LocalAccountTempDataSaver, LocalAccountTempDataProvider {

    private val emailKey = "emailKey"
    private val passwordKey = "passwordKey"

    override suspend fun saveEmail(email: String) = dataStore.set(emailKey, email)

    override suspend fun savePassword(password: String) = dataStore.set(passwordKey, password)

    override fun getEmailByFlow(): Flow<String> = dataStore.getByFlow(emailKey, "", String::class)

    override fun getPasswordByFlow(): Flow<String> = dataStore.getByFlow(passwordKey, "", String::class)

    override suspend fun getEmail(): String = dataStore.get(emailKey, "", String::class)

    override suspend fun getPassword(): String = dataStore.get(passwordKey, "", String::class)
}