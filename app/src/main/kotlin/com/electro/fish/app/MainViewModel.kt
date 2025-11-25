package com.electro.fish.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.electro.fish.data.AuthTokenProvider
import com.electro.fish.data.model.Role
import com.electro.fish.navigation.ProfileSetupScreen
import com.electro.fish.navigation.WelcomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val authTokenProvider: AuthTokenProvider,
) : ViewModel() {
    val state = authTokenProvider.getTokenByFlow().map { token ->
        if (token.isEmpty()) return@map MainState(WelcomeScreen)

        val role = JWT(token).getClaim("role").asObject(Role::class.java)
        val fullName = JWT(token).getClaim("fullName").asString()

        Log.d("debug", fullName.toString())

        if(fullName == "null null") {
            MainState(ProfileSetupScreen)
        } else {
            MainState(WelcomeScreen)

        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainState())
}