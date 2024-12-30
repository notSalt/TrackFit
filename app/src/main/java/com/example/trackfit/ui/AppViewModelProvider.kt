package com.example.trackfit.ui

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trackfit.ui.screens.activitylog.ActivityLogViewModel
import com.example.trackfit.TrackFitApplication
import com.example.trackfit.ui.screens.activitylog.AddActivityViewModel
import com.example.trackfit.ui.screens.login.LoginViewModel
import com.example.trackfit.ui.screens.nutrigo.AddMealViewModel
import com.example.trackfit.ui.screens.nutrigo.NutriGoViewModel
import com.example.trackfit.ui.screens.register.RegisterViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            RegisterViewModel(
                trackFitApplication().container.userRepository
            )
        }

        initializer {
            LoginViewModel(
                trackFitApplication().container.userRepository
            )
        }

        initializer {
            ActivityLogViewModel(
                trackFitApplication().container.activityRepository
            )
        }

        initializer {
            AddActivityViewModel(
                trackFitApplication().container.activityRepository
            )
        }

        initializer {
            NutriGoViewModel(
                trackFitApplication().container.mealRepository
            )
        }

        initializer {
            AddMealViewModel(
                trackFitApplication().container.mealRepository
            )
        }
    }
}

fun CreationExtras.trackFitApplication(): TrackFitApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TrackFitApplication)