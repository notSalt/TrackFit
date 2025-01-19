package com.example.trackfit.ui.screens.nutrigo

import com.example.trackfit.model.Meal

data class AddMealUiState(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val calories: Int = 0,
)

fun AddMealUiState.toMeal(): Meal = Meal(
    id = id,
    name = name,
    category = category,
    calories = calories
)

fun Meal.toAddMealUiState(): AddMealUiState = AddMealUiState(
    id = id,
    name = name,
    category = category,
    calories = calories
)