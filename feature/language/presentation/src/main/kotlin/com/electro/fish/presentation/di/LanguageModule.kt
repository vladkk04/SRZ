package com.electro.fish.presentation.di

import com.electro.fish.domain.LanguageManager
import com.electro.fish.presentation.language.LanguageManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface LanguageModule {

    @Binds
    @ViewModelScoped
    fun bindLanguageManager(impl: LanguageManagerImpl): LanguageManager
}