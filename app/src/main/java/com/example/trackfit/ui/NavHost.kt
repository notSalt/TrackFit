package com.example.trackfit.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackfit.ui.bmicalculator.BmiCalculatorScreen
import com.example.trackfit.ui.dashboard.DashboardScreen
import com.example.trackfit.ui.login.LoginScreen
import com.example.trackfit.ui.register.RegisterScreen
import com.example.trackfit.ui.waterintake.WaterIntakeScreen
import com.example.trackfit.ui.welcome.WelcomeScreen
import com.example.trackfit.utils.Routes

@Composable
fun NavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {
        composable(Routes.WELCOME) {
            Surface {
                WelcomeScreen(navController)
            }
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController)
        }

        composable(Routes.BMI_CALCULATOR) {
            BmiCalculatorScreen()
        }

        composable(Routes.WORKOUT_GUIDE) {

        }

        composable(Routes.STEP_COUNTER) {

        }

        composable(Routes.ACTIVITY_LOG) {

        }

        composable(Routes.WATER_INTAKE) {
            WaterIntakeScreen()
        }

        composable(Routes.NUTRI_GO) {

        }
    }
}