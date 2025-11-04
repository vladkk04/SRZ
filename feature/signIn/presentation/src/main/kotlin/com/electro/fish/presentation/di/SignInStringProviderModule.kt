package com.electro.fish.presentation.di

import com.electro.fish.domain.resources.SignInStringProvider
import com.electro.essential.resources.StringProvider
import com.electro.fish.presentation.resources.SignInStringProviderImpl
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