package com.example.trackfit.ui.screens.dashboard

import com.example.trackfit.model.service.AccountService
import com.example.trackfit.model.service.LogService
import com.example.trackfit.ui.screens.TrackFitViewModel
import com.example.trackfit.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    logService: LogService,
    private val accountService: AccountService,
) : TrackFitViewModel(logService) {
    val currentUser = accountService.currentUser

    fun openBMICalculator(openScreen: (String) -> Unit) {
        openScreen(Routes.BMI_CALCULATOR)
    }

    fun openWorkoutGuide(openScreen: (String) -> Unit) {
        openScreen(Routes.WORKOUT_GUIDE)
    }

    fun openDailyWaterIntake(openScreen: (String) -> Unit) {
        openScreen(Routes.WATER_INTAKE)
    }

    fun openStepCounter(openScreen: (String) -> Unit) {
        openScreen(Routes.STEP_COUNTER)
    }

    fun openNutriGo(openScreen: (String) -> Unit) {
        openScreen(Routes.NUTRI_GO)
    }

    fun openActivityLog(openScreen: (String) -> Unit) {
        openScreen(Routes.ACTIVITY_LOG)
    }

    fun onLogoutClick(clearAndNavigate: (String) -> Unit) {
        launchCatching {
            accountService.logOut()
            clearAndNavigate(Routes.LOGIN)
        }
    }
}