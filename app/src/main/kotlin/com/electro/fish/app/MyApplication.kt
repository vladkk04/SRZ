package com.electro.fish.app

import android.app.Application
import com.electro.essential.exception.mapper.DefaultExceptionToMessageMapper
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()
        ExceptionToMessageMapper.init(exceptionToMessageMapper)
    }
}