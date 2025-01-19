package com.example.trackfit

import WorkoutGuideScreen
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.common.snackbar.SnackbarManager
import com.example.trackfit.ui.screens.activitylog.ActivitiesScreen
import com.example.trackfit.ui.screens.activitylog.AddActivityScreen
import com.example.trackfit.ui.screens.bmicalculator.BmiCalculatorScreen
import com.example.trackfit.ui.screens.dashboard.DashboardScreen
import com.example.trackfit.ui.screens.login.LoginScreen
import com.example.trackfit.ui.screens.nutrigo.AddMealScreen
import com.example.trackfit.ui.screens.nutrigo.NutriGoScreen
import com.example.trackfit.ui.screens.onboarding.ActivityLogWelcome
import com.example.trackfit.ui.screens.onboarding.BmiWelcome
import com.example.trackfit.ui.screens.onboarding.NutriGoWelcome
import com.example.trackfit.ui.screens.onboarding.StepCounterWelcome
import com.example.trackfit.ui.screens.onboarding.WaterIntakeWelcome
import com.example.trackfit.ui.screens.onboarding.WorkoutWelcome
import com.example.trackfit.ui.screens.register.RegisterScreen
import com.example.trackfit.ui.screens.stepcounter.StepCounterScreen
import com.example.trackfit.ui.screens.waterintake.WaterIntakeScreen
import com.example.trackfit.ui.screens.welcome.WelcomeScreen
import com.example.trackfit.ui.theme.TrackFitTheme
import com.example.trackfit.utils.Routes
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterial3Api
fun TrackFitApp() {
    val appState = rememberAppState()

    TrackFitTheme {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = appState.snackbarHostState,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = Routes.WELCOME,
                modifier = Modifier.padding(innerPadding)
            ) {
                trackFitGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
    TrackFitAppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterial3Api
fun NavGraphBuilder.trackFitGraph(appState: TrackFitAppState) {
    composable(Routes.WELCOME) {
        WelcomeScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.LOGIN) {
        LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.REGISTER) {
        RegisterScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(Routes.DASHBOARD) {
        DashboardScreen(
            openScreen = { route -> appState.navigate(route) },
            clearAndNavigate = { route -> appState.clearAndNavigate(route) }
        )
    }

    composable(Routes.BMI_CALCULATOR) {
        BmiCalculatorScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.WORKOUT_GUIDE) {
        WorkoutGuideScreen(
            navigateBack = { appState.popUp() },
            onVideoClick = { videoUrl ->
                val context = appState.navController.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
                context.startActivity(intent)
            }
        )
    }

    composable(Routes.STEP_COUNTER) {
        StepCounterScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.ACTIVITY_LOG) {
        ActivitiesScreen(
            navigateBack = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(Routes.ADD_ACTIVITY) {
        AddActivityScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.WATER_INTAKE) {
        WaterIntakeScreen(
            navigateBack = { appState.popUp() }
        )
    }

    composable(Routes.NUTRI_GO) {
        NutriGoScreen(
            navigateBack = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(Routes.ADD_MEAL) {
        AddMealScreen(
            navigateBack = { appState.popUp() }
        )
    }


    // Onboarding
    composable(Routes.BMI_WELCOME) {
        BmiWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.navigate(Routes.STEP_COUNTER_WELCOME) }
        )
    }

    composable(Routes.STEP_COUNTER_WELCOME) {
        StepCounterWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.navigate(Routes.WATER_INTAKE_WELCOME) }
        )
    }

    composable(Routes.WATER_INTAKE_WELCOME) {
        WaterIntakeWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.navigate(Routes.WORKOUT_WELCOME) }
        )
    }

    composable(Routes.WORKOUT_WELCOME) {
        WorkoutWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.navigate(Routes.ACTIVITY_WELCOME) }
        )
    }

    composable(Routes.ACTIVITY_WELCOME) {
        ActivityLogWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.navigate(Routes.NUTRI_GO_WELCOME) }
        )
    }

    composable(Routes.NUTRI_GO_WELCOME) {
        NutriGoWelcome(
            pressBack = { appState.popUp() },
            pressForward = { appState.clearAndNavigate(Routes.DASHBOARD) }
        )
    }
}