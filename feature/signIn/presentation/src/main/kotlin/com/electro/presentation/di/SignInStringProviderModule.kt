package com.electro.presentation.di

import com.electro.domain.resources.SignInStringProvider
import com.electro.essential.resources.StringProvider
import com.electro.presentation.resources.SignInStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SignInStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SignInStringProvider::class)
    fun bindSignInStringProviderIntoMap(impl: SignInStringProviderImpl): StringProvider

    @Binds
    fun bindSignInStringProvider(impl: SignInStringProviderImpl): SignInStringProvider
}