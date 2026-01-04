package com.electro.fish.data.network.di

import com.electro.essential.FileRepository
import com.electro.fish.data.network.NetworkConfig
import com.electro.fish.data.network.client.createKtorClient
import com.electro.fish.data.network.image.FileRepositoryImpl
import com.electro.fish.data.network.interceptor.AuthInterceptionTokenProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton
/*
@Module
@InstallIn(SingletonComponent::class)
object TTModule {


}*/

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    companion object {
        @Provides
        @Singleton
        fun provideKtorClient(authInterceptionTokenProvider: AuthInterceptionTokenProvider): HttpClient = createKtorClient(
            NetworkConfig.timeout,
            authInterceptionTokenProvider
        )
    }

    @Binds
    @Singleton
    fun provideFileRepository(fileRepositoryImpl: FileRepositoryImpl): FileRepository
}
