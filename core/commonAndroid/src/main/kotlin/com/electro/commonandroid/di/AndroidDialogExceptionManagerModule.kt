package com.electro.commonandroid.di

import com.electro.commonandroid.dialog.DialogExceptionHandlerImpl
import com.electro.essential.DialogExceptionHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AndroidDialogExceptionManagerModule {

    @Binds
    fun bindAndroidDialogExceptionManager(impl: DialogExceptionHandlerImpl): DialogExceptionHandler

}