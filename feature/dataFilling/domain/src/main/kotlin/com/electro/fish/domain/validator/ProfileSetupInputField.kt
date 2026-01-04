package com.electro.fish.domain.validator

import com.electro.essential.validator.BaseInputField
import com.electro.essential.validator.InputFieldPatternRegex
import com.electro.fish.domain.resources.DataFillingStringProvider
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentMapOf

sealed interface ProfileSetupInputField {

    data object FirstName: BaseInputField.TextInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.firstName }
    }

    data object LastName: BaseInputField.TextInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.lastName }
    }

    data object PhoneNumber: BaseInputField.TextInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.phoneNumber }
        override val regexErrorResolverMap: ImmutableMap<Regex, ((DataFillingStringProvider) -> String)?> =
            persistentMapOf(InputFieldPatternRegex.phoneNumberRegex to { it.invalidPhoneNumberError })
    }

    data object Address: BaseInputField.TextInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.address }
    }

    data object ExpiryDate: BaseInputField.TextInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.expiryDate }
    }

    data object FishingLicenseImage: BaseInputField.ImageInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.fishingLicence }
    }

    data object FishingTicketImage: BaseInputField.ImageInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.fishingTicket }
    }

    data object MembershipMark : BaseInputField.ImageInputField<DataFillingStringProvider> {
        override val fieldName: (DataFillingStringProvider) -> String = { it.membershipMark }
    }
}