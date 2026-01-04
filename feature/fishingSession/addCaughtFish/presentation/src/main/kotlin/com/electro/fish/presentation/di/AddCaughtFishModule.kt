package com.electro.fish.presentation.di

import com.electro.fish.domain.resources.AddCaughtFishStringProvider
import com.electro.fish.presentation.resources.AddCaughtFishStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AddCaughtFishModule {

    @Binds
    fun bindAddCaughtFishStringProvider(impl: AddCaughtFishStringProviderImpl): AddCaughtFishStringProvider
}