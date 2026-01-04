package com.electro.fish.data.profile.di

import com.electro.fish.data.profile.ProfileRepository
import com.electro.fish.data.profile.remote.GetProfileDataApi
import com.electro.fish.data.profile.remote.api.GetProfileDataApiImpl
import com.electro.fish.data.profile.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ProfileModule {

    @Binds
    fun bindCreateAccountApi(impl: GetProfileDataApiImpl): GetProfileDataApi

    @Binds
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository
}