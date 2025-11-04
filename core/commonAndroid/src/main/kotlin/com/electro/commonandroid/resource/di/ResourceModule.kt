package com.electro.commonandroid.resource.di

import com.electro.commonandroid.resource.CoreStringProviderImpl
import com.electro.commonandroid.resource.ValidationStringProviderImpl
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
    fun bindValidationStringProviderIntoMap(impl: ValidationStringProviderImpl): StringProvider

    @Binds
    fun bindValidationStringProvider(impl: ValidationStringProviderImpl): ValidationStringProvider
}