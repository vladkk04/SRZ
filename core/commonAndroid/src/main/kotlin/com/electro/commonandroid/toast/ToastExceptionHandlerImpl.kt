package com.electro.commonandroid.toast

import android.content.Context
import android.widget.Toast
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import com.electro.essential.ToastExceptionHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastExceptionHandlerImpl @Inject constructor(
    @param: ApplicationContext private val context: Context
): ToastExceptionHandler {
    override fun showExceptionToast(exception: Exception) {
        Toast.makeText(context, ExceptionToMessageMapper.getLocalizedMessage(exception), Toast.LENGTH_SHORT).show()
    }
}