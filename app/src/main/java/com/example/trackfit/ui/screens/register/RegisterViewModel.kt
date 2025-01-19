package com.example.trackfit.ui.screens.register

import androidx.compose.runtime.mutableStateOf
import com.example.trackfit.common.ext.isValidEmail
import com.example.trackfit.common.ext.isValidPassword
import com.example.trackfit.common.ext.passwordMatches
import com.example.trackfit.common.snackbar.SnackbarManager
import com.example.trackfit.model.service.AccountService
import com.example.trackfit.model.service.LogService
import com.example.trackfit.ui.screens.TrackFitViewModel
import com.example.trackfit.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.trackfit.R.string as AppText

@HiltViewModel
class RegisterViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService
) : TrackFitViewModel(logService) {
    var uiState = mutableStateOf(RegisterUiState())
        private set

    private val firstName
        get() = uiState.value.firstName
    private val lastName
        get() = uiState.value.lastName
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onFirstNameChange(newValue: String) {
        uiState.value = uiState.value.copy(firstName = newValue)
    }

    fun onLastNameChange(newValue: String) {
        uiState.value = uiState.value.copy(lastName = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onRegisterClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.register(firstName, lastName, email, password)
            openAndPopUp(Routes.BMI_WELCOME, Routes.REGISTER)
        }
    }

    fun onAlreadyRegistered(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Routes.LOGIN, Routes.REGISTER)
    }
}

data class RegisterUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)
