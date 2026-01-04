package com.electro.fish.domain.usecase

import com.electro.fish.data.profile.ProfileRepository
import javax.inject.Inject

class GetProfileDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke() = profileRepository.getProfileData()
}
