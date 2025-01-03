package com.example.trackfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.trackfit.ui.theme.TrackFitTheme

class MainActivity : ComponentActivity() {
    private lateinit var loginStateManager: LoginStateManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TrackFitTheme {
                TrackFitApp()
            }
        }
    }
}