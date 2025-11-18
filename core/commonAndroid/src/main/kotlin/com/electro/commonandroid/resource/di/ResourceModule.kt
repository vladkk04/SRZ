package com.electro.commonandroid.resource.di

import com.electro.commonandroid.resource.CoreStringProviderImpl
import com.electro.commonandroid.resource.validation.AuthValidationStringProviderImpl
import com.electro.commonandroid.resource.validation.ValidationStringProviderImpl
import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.CoreStringProvider
import com.electro.essential.resources.StringProvider
import com.electro.essential.resources.ValidationStringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface ResourceModule {

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindCoreStringProviderIntoMap(impl: CoreStringProviderImpl): StringProvider

    @Binds
    fun bindCoreStringProvider(impl: CoreStringProviderImpl): CoreStringProvider


    @Binds
    @IntoMap
    @ClassKey(ValidationStringProvider::class)
    fun bindValidationStringProviderIntoMap(impl: AuthValidationStringProviderImpl): StringProvider

    @Binds
    @IntoMap
    @ClassKey(AuthValidationStringProvider::class)
    fun bindAuthValidationStringProviderIntoMap(impl: AuthValidationStringProviderImpl): StringProvider

    @Binds
    fun bindValidationStringProvider(impl: AuthValidationStringProviderImpl): ValidationStringProvider

    @Binds
    fun bindAuthValidationStringProvider(impl: AuthValidationStringProviderImpl): AuthValidationStringProvider
}