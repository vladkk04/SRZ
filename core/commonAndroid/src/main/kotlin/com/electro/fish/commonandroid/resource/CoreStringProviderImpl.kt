package com.electro.fish.commonandroid.resource

import android.content.Context
import com.electro.essential.resources.CoreStringProvider
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): CoreStringProvider {
    override fun clientErrorMessage(code: Int, message: String): String =
        context.getString(R.string.commonAndroid_http_error_message, code, message)
}