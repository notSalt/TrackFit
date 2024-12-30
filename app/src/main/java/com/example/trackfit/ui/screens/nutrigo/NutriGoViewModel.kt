package com.example.trackfit.ui.screens.nutrigo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackfit.data.meal.Meal
import com.example.trackfit.data.meal.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NutriGoViewModel(private val mealRepository: MealRepository) : ViewModel() {
    private val _mealLog = MutableStateFlow<List<Meal>>(emptyList())
    val mealLog: StateFlow<List<Meal>> = _mealLog.asStateFlow()

    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories.asStateFlow()

    init {
        loadMealLog()
    }

    private fun loadMealLog() {
        viewModelScope.launch {
            mealRepository.getAllMealsStream().collect { meals ->
                _mealLog.value = meals
                _totalCalories.value = meals.sumOf { it.calories }
            }
        }
    }
}