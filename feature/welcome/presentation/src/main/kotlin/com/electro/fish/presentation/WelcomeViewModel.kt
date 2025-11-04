package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import com.electro.fish.presentation.navigation.WelcomeNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val navigator: WelcomeNavigator
): ViewModel() {

    fun launchSignInScreen() {
        navigator.launchSignInScreen()
    }

    fun launchSignUpScreen() {
        navigator.launchSignUpScreen()
    }
}