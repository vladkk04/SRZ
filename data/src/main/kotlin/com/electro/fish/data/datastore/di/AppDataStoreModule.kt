package com.electro.fish.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.appDataStore by preferencesDataStore(name = "app_data")

@Module
@InstallIn(SingletonComponent::class)
object AppDataStoreModule {

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.appDataStore

}