package com.electro.fish.data.profile.repository

import com.electro.fish.data.profile.ProfileRepository
import com.electro.fish.data.profile.model.ProfileData
import com.electro.fish.data.profile.remote.GetProfileDataApi
import com.electro.fish.data.profile.remote.dto.toProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val getProfileDataApi: GetProfileDataApi
): ProfileRepository {
    override fun getProfileData(): Flow<ProfileData> = flow {
        emit(getProfileDataApi.getProfileData().toProfileData())
    }
}