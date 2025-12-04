package com.electro.fish.presentation

sealed interface WelcomeEvent {
    data object SignIn : WelcomeEvent
    data object SignUp : WelcomeEvent
}