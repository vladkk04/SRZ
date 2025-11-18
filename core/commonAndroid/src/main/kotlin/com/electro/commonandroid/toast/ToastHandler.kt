package com.electro.commonandroid.toast

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.electro.essential.ToastExceptionHandler
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastHandler @Inject constructor(
    @param: ApplicationContext private val context: Context
): ToastExceptionHandler {
    override fun handleException(exception: Exception) {
        Toast.makeText(context, ExceptionToMessageMapper.getLocalizedMessage(exception), Toast.LENGTH_SHORT).show()
    }
}