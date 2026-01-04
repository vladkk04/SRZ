package com.electro.fish.data.account.auth.signUp.di

import com.electro.fish.data.account.auth.signUp.CreateAccountRepository
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataProvider
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataSaver
import com.electro.fish.data.account.auth.signUp.local.LocalAccountTempData
import com.electro.fish.data.account.auth.signUp.repository.CreateAccountRepositoryImpl
import com.electro.fish.data.account.auth.signUp.remote.api.CreateAccountApiImpl
import com.electro.fish.data.account.auth.signUp.remote.CreateAccountApi
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

    @Binds
    fun bindLocalAccountTempSaver(impl: LocalAccountTempData): LocalAccountTempDataSaver

    @Binds
    fun bindLocalAccountTempProvider(impl: LocalAccountTempData): LocalAccountTempDataProvider
}