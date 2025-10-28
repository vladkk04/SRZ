package com.electro.fish.navigation.di

import com.electro.fish.navigation.base.AppNavigator
import com.electro.fish.navigation.base.NavComponentAppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AppNavigatorModule {

    @Binds
    fun bindAppNavigator(impl: NavComponentAppNavigator): AppNavigator
}