package com.example.trackfit.ui.screens.nutrigo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import com.example.trackfit.common.ext.idFromParameter
import com.example.trackfit.model.Meal
import com.example.trackfit.model.service.LogService
import com.example.trackfit.model.service.StorageService
import com.example.trackfit.ui.screens.TrackFitViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMealViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    logService: LogService,
    private val storageService: StorageService
) : TrackFitViewModel(logService) {
    var uiState = mutableStateOf(AddMealUiState())
        private set

    init {
        val mealId = savedStateHandle.get<String>(MEAL_ID)
        if (mealId != null) {
            launchCatching {
                val meal =
                    storageService.getMeal(mealId.idFromParameter()) ?: Meal()
                uiState.value = meal.toAddMealUiState()
            }
        }
    }

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(name = newValue)
    }

    fun onCategoryChange(newValue: String) {
        uiState.value = uiState.value.copy(category = newValue)
    }

    fun onCalorieChange(newValue: Int) {
        uiState.value = uiState.value.copy(calories = newValue)
    }

    fun onAddClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedMeal = uiState.value.toMeal()
            if (editedMeal.id.isBlank()) {
                storageService.saveMeal(editedMeal)
            } else {
                storageService.updateMeal(editedMeal)
            }
            popUpScreen()
        }
    }

    fun onBackClick(popUp: () -> Unit) = popUp()

    companion object {
        private const val MEAL_ID = "mealId"
    }
}