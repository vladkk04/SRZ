package com.electro.fish.data.account.forgotPassword.di

import com.electro.fish.data.account.forgotPassword.remote.api.ForgotPasswordApiImpl
import com.electro.fish.data.account.forgotPassword.repository.ForgotPasswordRepositoryImpl
import com.electro.fish.data.account.forgotPassword.remote.ForgotPasswordApi
import com.electro.fish.data.account.forgotPassword.ForgotPasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ForgotPasswordModule {

    @Binds
    fun bindForgotPasswordApi(impl: ForgotPasswordApiImpl): ForgotPasswordApi

    @Binds
    fun bindForgotPasswordRepository(impl: ForgotPasswordRepositoryImpl): ForgotPasswordRepository

}