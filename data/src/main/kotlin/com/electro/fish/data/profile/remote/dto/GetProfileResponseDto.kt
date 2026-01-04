package com.electro.fish.data.profile.remote.dto

import android.R.attr.password
import com.electro.fish.data.account.auth.model.Role
import com.electro.fish.data.profile.model.ProfileData
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileResponseDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val birthDate: String,
    val role: Role,
    val address: String,
    val licenseExpiryDate: String,
    val fishingLicensePhotoUrl: String?,
    val fishingTicketPhotoUrl: String?,
    val membershipMarkPhotoUrl: String?
)

fun GetProfileResponseDto.toProfileData() = ProfileData(
    id = id,
    firstName = firstName,
    lastName = lastName,
    phoneNumber = phoneNumber,
    birthDate = birthDate,
    email = email,
    role = role,
    address = address,
    licenseExpiryDate = licenseExpiryDate,
    fishingLicensePhotoUrl = fishingLicensePhotoUrl,
    fishingTicketPhotoUrl = fishingTicketPhotoUrl,
    membershipMarkPhotoUrl = membershipMarkPhotoUrl
)
