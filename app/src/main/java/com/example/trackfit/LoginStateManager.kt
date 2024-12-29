package com.example.trackfit

import android.content.Context
import android.content.SharedPreferences

class LoginStateManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val USER_FULLNAME = "user_fullname"
    }

    fun logIn(userFullName: String) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, true).apply()
        sharedPreferences.edit().putString(USER_FULLNAME, userFullName).apply()
    }

    fun logOut() {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN, false).apply()
        sharedPreferences.edit().remove(USER_FULLNAME).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun getUserFullName(): String? {
        return sharedPreferences.getString(USER_FULLNAME, null)
    }
}