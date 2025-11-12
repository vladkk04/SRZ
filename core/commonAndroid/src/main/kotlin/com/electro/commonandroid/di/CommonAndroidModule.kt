package com.electro.commonandroid.di

import com.electro.commonandroid.customTabs.AndroidCustomTab
import com.electro.commonandroid.toast.ToastExceptionHandlerImpl
import com.electro.essential.BrowserCustomTab
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import com.electro.essential.ToastExceptionHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindExceptionToMessageMapper(impl: DefaultExceptionToMessageMapper): ExceptionToMessageMapper

    @Binds
    fun bindAndroidToastExceptionManager(impl: ToastExceptionHandlerImpl): ToastExceptionHandler

    @Binds
    fun bindAndroidCustomTab(impl: AndroidCustomTab): BrowserCustomTab

}