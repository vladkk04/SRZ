package com.electro.commonandroid.di

import com.electro.commonandroid.AndroidExceptionHandler
import com.electro.commonandroid.CoreStringProviderImpl
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import com.electro.essential.handler.ExceptionHandler
import com.electro.essential.resources.CoreStringProvider
import com.electro.essential.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindCoreStringProvider(impl: CoreStringProviderImpl): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(impl: DefaultExceptionToMessageMapper): ExceptionToMessageMapper

    @Binds
    fun bindExceptionHandler(impl: AndroidExceptionHandler): ExceptionHandler

}