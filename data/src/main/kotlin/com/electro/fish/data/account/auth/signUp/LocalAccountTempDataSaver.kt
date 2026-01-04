package com.electro.fish.data.account.auth.signUp

interface LocalAccountTempDataSaver {
    suspend fun saveEmail(email: String)
    suspend fun savePassword(password: String)
}