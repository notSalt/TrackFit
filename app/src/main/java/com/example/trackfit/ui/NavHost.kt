package com.example.trackfit.ui

import android.content.Intent
import android.net.Uri
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
import WorkoutGuideScreen
import androidx.compose.ui.platform.LocalContext
import com.example.trackfit.ui.screens.activitylog.ActivityLogWelcome
import com.example.trackfit.ui.screens.bmicalculator.BmiWelcome
import com.example.trackfit.ui.screens.bmicalculator.BmiWelcomePreview
import com.example.trackfit.ui.screens.nutrigo.NutriGoWelcome
import com.example.trackfit.ui.screens.stepcounter.StepCounterWelcome
import com.example.trackfit.ui.screens.waterintake.WaterIntakeWelcome
import com.example.trackfit.ui.screens.workoutguide.WorkoutWelcome

@Composable
fun NavHost(
    navController: NavHostController
) {
    val context = LocalContext.current

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
            LoginScreen(navController, context = context)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(navController, context)
        }

        composable(Routes.BMI_CALCULATOR) {
            BmiCalculatorScreen(navController)
        }

        composable(Routes.WORKOUT_GUIDE) {
            WorkoutGuideScreen(
                navController = navController,
                onVideoClick = { videoUrl ->
                    val context = navController.context
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                    context.startActivity(intent)
                }
            )
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
        composable(Routes.BMI_WELCOME) {
            BmiWelcome(navController)
        }
        composable(Routes.STEP_COUNTER_WELCOME) {
            StepCounterWelcome(navController)
        }
        composable(Routes.WATER_INTAKE_WELCOME) {
            WaterIntakeWelcome(navController)
        }
        composable(Routes.WORKOUT_WELCOME) {
            WorkoutWelcome(navController)
        }
        composable(Routes.ACTIVITY_WELCOME) {
            ActivityLogWelcome(navController)
        }
        composable(Routes.NUTRI_GO_WELCOME) {
            NutriGoWelcome(navController)
        }
    }
}
