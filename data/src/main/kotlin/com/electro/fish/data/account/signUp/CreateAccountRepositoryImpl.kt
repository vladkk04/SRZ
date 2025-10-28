package com.electro.fish.data.account.signUp

import com.electro.fish.data.CreateAccountRepository
import com.electro.fish.data.account.signIn.remote.dto.SignInRequestDto
import com.electro.fish.data.account.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.signUp.remote.dto.SignUpRequestDto
import com.electro.fish.data.model.Token
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CreateAccountRepositoryImpl @Inject constructor(
    private val api: CreateAccountApi
): CreateAccountRepository {

    override suspend fun createAccount(createAccountCredentials: CreateAccountCredentials) {
        val request = SignUpRequestDto(
            fistName = createAccountCredentials.firstName,
            lastName = createAccountCredentials.lastName,
            birthDate = createAccountCredentials.birthDate,
            email = createAccountCredentials.email,
            password = createAccountCredentials.password,
            role = createAccountCredentials.role,
            address = createAccountCredentials.address,
            fishingLicenseNumber = createAccountCredentials.fishingLicenseNumber
        )

        return api.signUp(request)
    }
}