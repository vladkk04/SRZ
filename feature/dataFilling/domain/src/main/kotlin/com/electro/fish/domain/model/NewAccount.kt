package com.electro.fish.domain.model

import android.net.Uri
import com.electro.essential.validator.InputFieldValue
import com.electro.essential.validator.WithFormInputValidator
import com.electro.fish.data.account.auth.signUp.model.CreateAccountCredentials
import com.electro.fish.data.account.auth.model.Role
import com.electro.fish.domain.validator.ProfileSetupInputField

data class NewAccountFull(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val phoneNumber: String,
    val address: String,
    val licenseExpiryDate: String,
    val fishingLicence: Uri,
    val fishingTicket: Uri,
    val membershipMark: Uri
)

class FullNameInputFieldValue(
    private val firstName: String,
    private val lastName: String
) : WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.FirstName, value = firstName),
        InputFieldValue(ProfileSetupInputField.LastName, value = lastName),
    )
}

class PhoneNumberInputFieldValue(
    private val phoneNumber: String
) : WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.PhoneNumber, value = phoneNumber),
    )
}

class AddressInputFieldValue(
    private val address: String
) : WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.Address, value = address),
    )
}

class FishingLicenceFieldValue(
    private val licence: Uri,
    private val licenceExpire: String
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.FishingLicenseImage, value = licence.path),
        InputFieldValue(ProfileSetupInputField.ExpiryDate, value = licenceExpire)
    )
}

class FishingTicketFieldValue(
    private val licence: Uri,
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.FishingLicenseImage, value = licence.path),
    )
}

class MembershipMarkFieldValue(
    private val licence: Uri,
): WithFormInputValidator {
    override fun toFieldValues(): List<InputFieldValue<*>> = listOf(
        InputFieldValue(ProfileSetupInputField.FishingLicenseImage, value = licence.path),
    )
}

fun NewAccountFull.mapToCreateAccountCredentials(email: String, password: String) = CreateAccountCredentials(
    firstName = firstName,
    lastName = lastName,
    birthday = birthDate,
    email = email,
    password = password,
    phoneNumber = phoneNumber,
    role = Role.GUEST,
    address = address,
    licenseExpiryDate = licenseExpiryDate
)