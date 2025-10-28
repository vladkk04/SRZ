package com.electro.fish.data.account.signUp.di

import com.electro.fish.data.AccountRepository
import com.electro.fish.data.CreateAccountRepository
import com.electro.fish.data.account.signIn.AccountApiImpl
import com.electro.fish.data.account.signIn.AccountRepositoryImpl
import com.electro.fish.data.account.signIn.remote.AccountApi
import com.electro.fish.data.account.signUp.CreateAccountRepositoryImpl
import com.electro.fish.data.account.signUp.remote.CreateAccountApi
import com.electro.fish.data.account.signUp.CreateAccountApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SignUpModule {

    @Binds
    fun bindCreateAccountRepository(impl: CreateAccountRepositoryImpl): CreateAccountRepository

    @Binds
    fun bindCreateAccountApi(impl: CreateAccountApiImpl): CreateAccountApi
}