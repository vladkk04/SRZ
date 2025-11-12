package com.electro.fish.data.network.di

import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.client.createKtorClient
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(authInterceptionTokenProvider: AuthInterceptionTokenProvider): HttpClient = createKtorClient(
        NetworkConfig.timeout,
        authInterceptionTokenProvider
    )
}