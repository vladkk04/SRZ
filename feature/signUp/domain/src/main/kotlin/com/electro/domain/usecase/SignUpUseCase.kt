package com.electro.domain.usecase

import android.net.Credentials
import android.util.Log
import com.electro.domain.model.NewAccount
import com.electro.domain.model.mapToCreateAccountCredentials
import com.electro.domain.validator.NewAccountValidator
import com.electro.essential.validator.ValidationResult
import com.electro.fish.data.AccountRepository
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
