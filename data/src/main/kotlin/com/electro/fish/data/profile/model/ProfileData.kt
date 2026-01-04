package com.electro.fish.data.profile.model

import com.electro.fish.data.account.auth.model.Role

data class ProfileData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val birthDate: String,
    val email: String,
    val role: Role,
    val address: String,
    val licenseExpiryDate: String,
    val fishingLicensePhotoUrl: String?,
    val fishingTicketPhotoUrl: String?,
    val membershipMarkPhotoUrl: String?
)
