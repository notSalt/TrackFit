package com.example.trackfit.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackfit.data.user.User
import com.example.trackfit.data.user.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerUser(firstName: String, lastName: String, email: String, password: String, onRegisterResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailStream(email).first()
            if (user != null) {
                onRegisterResult(false, "Email has already been taken.")
            } else {
                val newUser = User(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password
                )

                try {
                    // Insert user without returning a result
                    userRepository.insertUser(newUser)

                    // Assuming that if we get to this point, the user was inserted successfully
                    onRegisterResult(true, "Registration successful.")
                } catch (e: Exception) {
                    onRegisterResult(false, "An error has occured.")
                }
            }
        }
    }
}