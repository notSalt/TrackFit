package com.example.trackfit.ui.screens.nutrigo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trackfit.data.meal.Meal
import com.example.trackfit.data.meal.MealRepository

class AddMealViewModel(private val mealRepository: MealRepository) : ViewModel() {
    var mealUiState by mutableStateOf(MealUiState())
        private set

    fun updateUiState(mealDetails: MealDetails) {
        mealUiState =
            MealUiState(mealDetails = mealDetails, isEntryValid = validateInput(mealDetails))
    }

    suspend fun saveMeal() {
        if (validateInput()) {
            mealRepository.insertMeal(mealUiState.mealDetails.toMeal())
        }
    }

    private fun validateInput(uiState: MealDetails = mealUiState.mealDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && category.isNotBlank() && calories != 0
        }
    }
}

data class MealDetails(
    val id: Int = 0,
    val name: String = "",
    val category: String = "",
    val calories: Int = 0,
)

data class MealUiState(
    val mealDetails: MealDetails = MealDetails(),
    val isEntryValid: Boolean = false
)

fun MealDetails.toMeal(): Meal = Meal(
    id = id,
    name = name,
    category = category,
    calories = calories,
)

fun Meal.toMealUiState(isEntryValid: Boolean = false): MealUiState = MealUiState(
    mealDetails = this.toMealDetails(),
    isEntryValid = isEntryValid
)

fun Meal.toMealDetails(): MealDetails = MealDetails(
    id = id,
    name = name,
    category = category,
    calories = calories
)