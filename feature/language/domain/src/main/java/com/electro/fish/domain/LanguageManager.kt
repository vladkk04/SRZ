package com.electro.fish.domain

import com.electro.fish.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow

interface LanguageManager {
    fun changeLanguage(language: AppLanguage)
    fun getCurrentLanguage(): Flow<AppLanguage>
}