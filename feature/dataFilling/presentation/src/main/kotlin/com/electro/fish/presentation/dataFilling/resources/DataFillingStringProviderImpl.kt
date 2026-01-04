package com.electro.fish.presentation.dataFilling.resources

import android.content.Context
import com.electro.fish.commonandroid.resource.validation.ValidationStringProviderImpl
import com.electro.fish.domain.resources.DataFillingStringProvider
import com.electro.fish.feature.dataFilling.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataFillingStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : ValidationStringProviderImpl(context), DataFillingStringProvider {
    override val firstName: String = context.getString(R.string.dataFilling_firstName)
    override val lastName: String = context.getString(R.string.dataFilling_lastName)
    override val phoneNumber: String = context.getString(R.string.dataFilling_phone_number)
    override val address: String = context.getString(R.string.dataFilling_address)
    override val expiryDate: String = context.getString(R.string.dataFilling_expiry_date)

    override val fishingLicence: String = context.getString(R.string.dataFilling_fishingLicence)
    override val fishingTicket: String = context.getString(R.string.dataFilling_fishingTicket)
    override val membershipMark: String = context.getString(R.string.dataFilling_membershipMark)

    override val invalidPhoneNumberError: String = context.getString(R.string.dataFilling_invalid_phone_number)
    override val userAlreadyExistError: String = context.getString(R.string.dataFilling_user_already_exist_error)
}