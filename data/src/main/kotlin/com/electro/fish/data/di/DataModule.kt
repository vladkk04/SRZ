package com.electro.fish.data.di

import com.electro.fish.data.AuthTokenProvider
import com.electro.fish.data.AuthTokenSaver
import com.electro.fish.data.LocalAuthTokenRepository
import com.electro.fish.data.local.LocalAuthTokenRepositoryImpl
import com.electro.fish.data.manager.AuthTokenManagerImpl
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindAuthTokenSaver(impl: AuthTokenManagerImpl): AuthTokenSaver

    @Binds
    fun bindAuthTokenProvider(impl: AuthTokenManagerImpl): AuthTokenProvider

    @Binds
    fun binAuthInterceptorTokenProvider(impl: AuthTokenManagerImpl): AuthInterceptionTokenProvider

    @Binds
    fun bindLocalTokenRepository(impl: LocalAuthTokenRepositoryImpl): LocalAuthTokenRepository

}