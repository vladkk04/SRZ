package com.electro.fish.data.account.auth.token.di

import com.electro.fish.data.account.auth.token.AuthTokenProvider
import com.electro.fish.data.account.auth.token.AuthTokenSaver
import com.electro.fish.data.account.auth.token.LocalAuthTokenRepository
import com.electro.fish.data.account.auth.token.manager.AuthTokenManagerImpl
import com.electro.fish.data.account.auth.token.repository.LocalAuthTokenRepositoryImpl
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TokenModule {

    @Binds
    fun bindAuthTokenSaver(impl: AuthTokenManagerImpl): AuthTokenSaver

    @Binds
    fun bindAuthTokenProvider(impl: AuthTokenManagerImpl): AuthTokenProvider

    @Binds
    fun bindLocalTokenRepository(impl: LocalAuthTokenRepositoryImpl): LocalAuthTokenRepository

    @Binds
    fun binAuthInterceptorTokenProvider(impl: AuthTokenManagerImpl): AuthInterceptionTokenProvider
}