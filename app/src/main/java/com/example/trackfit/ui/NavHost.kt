package com.example.trackfit.ui

import WorkoutGuideScreen
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trackfit.ui.screens.activitylog.ActivityLogScreen
import com.example.trackfit.ui.screens.activitylog.AddActivityScreen
import com.example.trackfit.ui.screens.bmicalculator.BmiCalculatorScreen
import com.example.trackfit.ui.screens.dashboard.DashboardScreen
import com.example.trackfit.ui.screens.login.LoginScreen
import com.example.trackfit.ui.screens.nutrigo.AddMealScreen
import com.example.trackfit.ui.screens.nutrigo.NutriGoScreen
import com.example.trackfit.ui.screens.register.RegisterScreen
import com.example.trackfit.ui.screens.stepcounter.StepCounterScreen
import com.example.trackfit.ui.screens.waterintake.WaterIntakeScreen
import com.example.trackfit.ui.screens.welcome.WelcomeScreen
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
            BmiCalculatorScreen(navController)
        }

        composable(Routes.WORKOUT_GUIDE) {
            WorkoutGuideScreen(navController)
        }

        composable(Routes.STEP_COUNTER) {
            StepCounterScreen(navController)
        }

        composable(Routes.ACTIVITY_LOG) {
            ActivityLogScreen(navController)
        }

        composable(Routes.WATER_INTAKE) {
            WaterIntakeScreen(navController)
        }

        composable(Routes.NUTRI_GO) {
            NutriGoScreen(navController)
        }
        composable(Routes.ADD_MEAL) {
            AddMealScreen(navController)
        }
        composable(Routes.ADD_ACTIVITY) {
            AddActivityScreen(navController)
        }
    }
}