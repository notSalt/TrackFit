package com.example.trackfit.ui.screens.nutrigo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackfit.model.service.StorageService
import com.example.trackfit.utils.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutriGoViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    val meals = storageService.meals

    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories.asStateFlow()

    init {
        refreshTotalCalories()
    }

    private fun refreshTotalCalories() {
        viewModelScope.launch {
            meals.collect { _meals ->
                _totalCalories.value = _meals.sumOf { it.calories }
            }
        }
    }

    fun onBackClick(popUpScreen: () -> Unit) = popUpScreen()

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(Routes.ADD_MEAL)
}