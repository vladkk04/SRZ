package com.electro.fish.data.account.auth.signIn.di

import com.electro.fish.data.account.auth.signIn.remote.api.AccountApiImpl
import com.electro.fish.data.account.auth.signIn.repository.AccountRepositoryImpl
import com.electro.fish.data.account.auth.signIn.remote.AccountApi
import com.electro.fish.data.account.auth.signIn.AccountRepository
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