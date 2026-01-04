package com.electro.fish.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.fish.data.account.auth.signUp.LocalAccountTempDataProvider
import com.electro.fish.data.account.auth.token.AuthTokenProvider
import com.electro.fish.navigation.DataFillingScreen
import com.electro.fish.navigation.TopLevel
import com.electro.fish.navigation.WelcomeScreen
import com.electro.fish.navigation.base.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: AppNavigator,
    private val authTokenProvider: AuthTokenProvider,
    private val localAccountTempDataProvider: LocalAccountTempDataProvider
) : ViewModel() {

    val state: StateFlow<MainState> = flow {
        val authToken = authTokenProvider.getTokenByFlow().first()
        val password = localAccountTempDataProvider.getPasswordByFlow().first()

        val screen = if(authToken.isNotEmpty()) {
            TopLevel.TopLevelGraph
        } else if(password.isNotEmpty()) {
            DataFillingScreen
        } else {
            WelcomeScreen
        }

        emit(MainState(startScreenDestination = screen))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainState())
}