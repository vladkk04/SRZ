package com.electro.fish.presentation.language

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.electro.fish.domain.model.AppLanguage
import com.electro.fish.domain.LanguageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class LanguageManagerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : LanguageManager {

    override fun changeLanguage(language: AppLanguage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(language.code)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.code))
        }
    }

    override fun getCurrentLanguage(): Flow<AppLanguage> {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java)?.applicationLocales?.get(0)
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)
        }

        val language = when (locale?.language) {
            "sk" -> AppLanguage.Slovak
            "en" -> AppLanguage.English
            else -> AppLanguage.English
        }

        return flowOf(language)
    }
}