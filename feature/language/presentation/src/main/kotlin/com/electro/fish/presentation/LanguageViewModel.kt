package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.fish.domain.model.AppLanguage
import com.electro.fish.domain.LanguageManager
import com.electro.fish.presentation.navigation.LanguageNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languageManager: LanguageManager,
    private val navigator: LanguageNavigator,
): ViewModel() {

    val state = languageManager.getCurrentLanguage()
        .map { LanguageState(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LanguageState())

    fun changeLanguage(language: AppLanguage) {
        languageManager.changeLanguage(language)
        navigator.goBack()
    }
}