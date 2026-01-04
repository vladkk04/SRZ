package com.electro.fish.commonandroid.customTabs

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import androidx.core.net.toUri
import com.electro.essential.BrowserCustomTab
import jakarta.inject.Singleton

@Singleton
class AndroidCustomTab @Inject constructor(
    @param: ApplicationContext private val context: Context
): BrowserCustomTab {
    override fun openCustomTab(url: String) {
        val intent = CustomTabsIntent.Builder().build()
        intent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.launchUrl(context, url.toUri())
    }
}