package com.electro.fish.data.profile

import com.electro.fish.data.profile.model.ProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileData(): Flow<ProfileData>
}