package com.electro.fish.domain.usecase

import com.electro.domain.model.NewAccount
import com.electro.domain.model.mapToCreateAccountCredentials
import com.electro.fish.data.CreateAccountRepository
import com.electro.fish.data.LocalTokenRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
   // private val newAccountValidator: NewAccountValidator,
    private val createAccountRepository: CreateAccountRepository,
    private val localTokenRepository: LocalTokenRepository
) {
    /**
     * @throws NewAccountValidationException
     */
    suspend operator fun invoke(account: NewAccount) {
       createAccountRepository.createAccount(account.mapToCreateAccountCredentials())

    }
}

/*
internal fun Credentials.validate() {
    if (email.isBlank()) {
        throw EmptyInputFieldException(InputField.Email)
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        throw EmailInvalidException()
    } else if (password.isBlank()) {
        throw EmptyInputFieldException(InputField.Password)
    }
}
*/
