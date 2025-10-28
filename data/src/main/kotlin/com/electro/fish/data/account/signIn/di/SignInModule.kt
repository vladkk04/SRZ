package com.electro.fish.data.account.signIn.di

import com.electro.fish.data.AccountRepository
import com.electro.fish.data.account.signIn.AccountApiImpl
import com.electro.fish.data.account.signIn.AccountRepositoryImpl
import com.electro.fish.data.account.signIn.remote.AccountApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SignInModule {

    @Binds
    fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    fun bindAccountApi(impl: AccountApiImpl): AccountApi
}