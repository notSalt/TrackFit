package com.example.trackfit

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.ui.NavHost

@Composable
fun TrackFitApp() {
    val navController = rememberNavController()
    NavHost(navController)
}