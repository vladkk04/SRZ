package com.electro.commonandroid.di

import com.electro.commonandroid.toast.AndroidToastManager
import com.electro.commonandroid.resource.CoreStringProviderImpl
import com.electro.commonandroid.resource.ValidationStringProviderImpl
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import com.electro.essential.manager.ToastManager
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
interface CommonAndroidModule {

    @Binds
    fun bindExceptionToMessageMapper(impl: DefaultExceptionToMessageMapper): ExceptionToMessageMapper

    @Binds
    fun bindToastManager(impl: AndroidToastManager): ToastManager

}