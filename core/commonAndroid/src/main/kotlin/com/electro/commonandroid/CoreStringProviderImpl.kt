package com.electro.commonandroid

import android.content.Context
import com.electro.essential.resources.CoreStringProvider
import com.electro.fish.core.commonAndroid.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CoreStringProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
): CoreStringProvider {
    override val connectionErrorMessage: String =
        context.getString(R.string.network_error_message)

    override val unknownErrorMessage: String
        get() = context.getString(R.string.unknown_error_message)

    override fun serverErrorMessage(code: Int, message: String): String =
        context.getString(R.string.server_error_message, code, message)
}