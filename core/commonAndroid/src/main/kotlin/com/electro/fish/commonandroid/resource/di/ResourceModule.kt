package com.electro.fish.commonandroid.resource.di


import com.electro.essential.resources.AuthValidationStringProvider
import com.electro.essential.resources.CoreStringProvider
import com.electro.essential.resources.StringProvider
import com.electro.essential.resources.ValidationStringProvider
import com.electro.fish.commonandroid.resource.CoreStringProviderImpl
import com.electro.fish.commonandroid.resource.validation.AuthValidationStringProviderImpl
import com.electro.fish.commonandroid.resource.validation.ValidationStringProviderImpl
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
    @ClassKey(AuthValidationStringProvider::class)
    fun bindAuthValidationStringProviderIntoMap(impl: AuthValidationStringProviderImpl): StringProvider

    @Binds
    fun bindAuthValidationStringProvider(impl: AuthValidationStringProviderImpl): AuthValidationStringProvider

}