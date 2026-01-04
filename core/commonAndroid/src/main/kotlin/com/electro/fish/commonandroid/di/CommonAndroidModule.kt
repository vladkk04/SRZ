package com.electro.fish.commonandroid.di

import com.electro.fish.commonandroid.customTabs.AndroidCustomTab
import com.electro.fish.commonandroid.dialog.DialogHandler
import com.electro.fish.commonandroid.toast.ToastHandler
import com.electro.essential.BrowserCustomTab
import com.electro.essential.DialogExceptionHandler
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
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
    fun bindAndroidDialogExceptionManager(impl: DialogHandler): DialogExceptionHandler

    @Binds
    fun bindAndroidToastExceptionManager(impl: ToastHandler): ToastExceptionHandler

    @Binds
    fun bindAndroidCustomTab(impl: AndroidCustomTab): BrowserCustomTab

}