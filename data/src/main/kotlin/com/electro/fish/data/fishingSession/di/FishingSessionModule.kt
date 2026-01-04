package com.electro.fish.data.fishingSession.di

import com.electro.fish.data.fishingSession.FishingSessionRepository
import com.electro.fish.data.fishingSession.LocalFishingCodeProvider
import com.electro.fish.data.fishingSession.LocalFishingCodeSaver
import com.electro.fish.data.fishingSession.local.LocalFishingCodeRepositoryImpl
import com.electro.fish.data.fishingSession.remote.FishingSessionApi
import com.electro.fish.data.fishingSession.remote.api.FishingSessionApiImpl
import com.electro.fish.data.fishingSession.repository.FishingSessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FishingSessionModule {

    @Binds
    fun bindFishingSessionApi(impl: FishingSessionApiImpl): FishingSessionApi

    @Binds
    fun bindFishingSessionRepository(impl: FishingSessionRepositoryImpl): FishingSessionRepository

   @Binds
   fun bindLocalFishingCodeProvider(impl: LocalFishingCodeRepositoryImpl): LocalFishingCodeProvider

   @Binds
   fun bindLocalFishingCodeSaver(impl: LocalFishingCodeRepositoryImpl): LocalFishingCodeSaver

}