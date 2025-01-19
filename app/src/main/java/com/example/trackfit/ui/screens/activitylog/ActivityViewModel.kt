package com.example.trackfit.ui.screens.activitylog

import androidx.lifecycle.ViewModel
import com.example.trackfit.model.service.StorageService
import com.example.trackfit.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    val activities = storageService.activities

    fun onBackClick(popUpScreen: () -> Unit) = popUpScreen()

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(Routes.ADD_ACTIVITY)
}