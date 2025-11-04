package com.electro.commonandroid.toast

import android.content.Context
import android.widget.Toast
import com.electro.essential.exception.mapper.ExceptionToMessageMapper
import com.electro.essential.manager.ToastManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidToastManager @Inject constructor(
    private val exceptionToMessageMapper: ExceptionToMessageMapper,
    @param: ApplicationContext private val context: Context
) : ToastManager {
    override fun handleException(exception: Exception) {
        Toast.makeText(context, exceptionToMessageMapper.getLocalizedMessage(exception), Toast.LENGTH_SHORT).show()
    }
}