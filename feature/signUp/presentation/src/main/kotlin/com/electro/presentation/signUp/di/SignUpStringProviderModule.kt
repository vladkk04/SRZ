package com.electro.presentation.signUp.di

import com.electro.domain.resources.SignUpStringProvider
import com.electro.essential.resources.StringProvider
import com.electro.presentation.signUp.resources.SignUpStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SignUpStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SignUpStringProviderModule::class)
    fun bindSignUpStringProviderIntoMap(impl: SignUpStringProviderImpl): StringProvider

    @Binds
    fun bindSignUpStringProvider(impl: SignUpStringProviderImpl): SignUpStringProvider
}