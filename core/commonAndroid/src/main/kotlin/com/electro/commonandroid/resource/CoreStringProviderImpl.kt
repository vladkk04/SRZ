package com.electro.commonandroid.resource

import android.content.Context
import com.electro.essential.resources.CoreStringProvider
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): CoreStringProvider {
    override val connectionErrorMessage: String =
        context.getString(R.string.commonAndroid_network_error_message)

    override val unknownErrorMessage: String
        get() = context.getString(R.string.commonAndroid_unknown_error_message)

    override fun serverErrorMessage(code: Int, message: String): String =
        context.getString(R.string.commonAndroid_server_error_message, code, message)
}