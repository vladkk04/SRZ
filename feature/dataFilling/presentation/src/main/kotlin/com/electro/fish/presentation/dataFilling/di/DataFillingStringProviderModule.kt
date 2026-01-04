package com.electro.fish.presentation.dataFilling.di

import com.electro.essential.resources.StringProvider
import com.electro.fish.domain.resources.DataFillingStringProvider
import com.electro.fish.presentation.dataFilling.resources.DataFillingStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface DataFillingStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(DataFillingStringProvider::class)
    fun bindDataFillingStringProviderIntoMap(impl: DataFillingStringProviderImpl): StringProvider

    @Binds
    fun bindDataFillingStringProvider(impl: DataFillingStringProviderImpl): DataFillingStringProvider
}