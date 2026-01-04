package com.electro.fish.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electro.fish.data.account.forgotPassword.model.Email
import com.electro.fish.domain.usecase.ForgotPasswordUseCase
import com.electro.fish.presentation.navigation.ForgotPasswordNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val navigator: ForgotPasswordNavigator,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
): ViewModel() {

    fun test() = viewModelScope.launch {
        forgotPasswordUseCase.invoke(Email("vladuslaw512@gmail.com"))
    }

    fun navigateBack() {
        navigator.navigateBack()
    }
}