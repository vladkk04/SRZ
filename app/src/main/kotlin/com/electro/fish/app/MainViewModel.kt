package com.electro.fish.app

import androidx.lifecycle.ViewModel
import com.electro.fish.data.AuthTokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val authTokenProvider: AuthTokenProvider,
) : ViewModel() {
   /* val state = authTokenProvider.getTokenByFlow().map { token ->
      *//*  if (token.isEmpty()) return@map MainState(WelcomeScreen)

        val role = JWT(token).getClaim("role").asObject(Role::class.java)
        val fullName = JWT(token).getClaim("fullName").asString()

        Log.d("debug", fullName.toString())*//*

        MainState(WelcomeScreen)

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainState())*/
}