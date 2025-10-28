package com.electro.fish.navigation.di

import com.electro.fish.navigation.navigators.SignInNavigatorImpl
import com.electro.fish.navigation.navigators.SignUpNavigatorImpl
import com.electro.fish.navigation.navigators.WelcomeNavigatorImpl
import com.electro.presentation.navigation.SignInNavigator
import com.electro.presentation.signUp.navigation.SignUpNavigator
import com.electro.presentation.navigation.WelcomeNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface NavigatorsModule {

    @Binds
    fun bindWelcomeNavigator(impl: WelcomeNavigatorImpl): WelcomeNavigator

    @Binds
    fun bindSignInNavigator(impl: SignInNavigatorImpl): SignInNavigator

    @Binds
    fun bindSignUpNavigator(impl: SignUpNavigatorImpl): SignUpNavigator


}