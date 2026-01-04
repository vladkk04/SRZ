package com.electro.fish.domain.resources

import com.electro.essential.resources.ValidationStringProvider

interface DataFillingStringProvider: ValidationStringProvider {
    val firstName: String
    val lastName: String
    val phoneNumber: String
    val address: String
    val expiryDate: String

    val fishingLicence: String
    val fishingTicket: String
    val membershipMark: String

    val invalidPhoneNumberError: String
    val userAlreadyExistError: String
}