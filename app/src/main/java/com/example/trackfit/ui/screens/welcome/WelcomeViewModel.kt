package com.example.trackfit.ui.screens.welcome

import androidx.lifecycle.ViewModel
import com.example.trackfit.model.service.AccountService
import com.example.trackfit.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    fun onGetStarted(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser) openAndPopUp(Routes.DASHBOARD, Routes.WELCOME)
        else openAndPopUp(Routes.REGISTER, Routes.WELCOME)
    }
}