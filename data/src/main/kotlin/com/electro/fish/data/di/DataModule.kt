package com.electro.fish.data.di

import com.electro.fish.data.LocalTokenRepository
import com.electro.fish.data.TokenManager
import com.electro.fish.data.TokenProvider
import com.electro.fish.data.local.LocalTokenRepositoryImpl
import com.electro.fish.data.manager.TokenManagerImpl
import com.electro.fish.data.network.interceptor.AuthTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindTokenManager(impl: TokenManagerImpl): TokenProvider

    @Binds
    fun bindTokenProvider(impl: TokenManagerImpl): AuthTokenProvider

    @Binds
    fun bindAccountRepository(impl: TokenManagerImpl): TokenManager

    @Binds
    fun bindLocalTokenRepository(impl: LocalTokenRepositoryImpl): LocalTokenRepository

}