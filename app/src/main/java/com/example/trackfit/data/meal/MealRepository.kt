package com.example.trackfit.data.meal

import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getAllMealsStream(): Flow<List<Meal>>

    fun getMealStream(id: Int): Flow<Meal?>

    suspend fun insertMeal(meal: Meal)

    suspend fun deleteMeal(meal: Meal)

    suspend fun updateMeal(meal: Meal)
}