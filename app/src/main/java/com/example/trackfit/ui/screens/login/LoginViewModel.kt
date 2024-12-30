package com.example.trackfit.ui.screens.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackfit.data.user.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import android.content.Context
import com.example.trackfit.LoginStateManager
import com.example.trackfit.data.user.User

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    // Check if the email and password match any existing user in the database
    fun loginUser(email: String, password: String, onLoginResult: (Boolean, User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailStream(email).first()
            if (user != null &&  user.password == password) {
                onLoginResult(true, user)  // Login successful
            } else {
                onLoginResult(false, null) // Login failed
            }
        }
    }
}