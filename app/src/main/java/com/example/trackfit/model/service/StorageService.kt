package com.example.trackfit.model.service

import com.example.trackfit.model.Activity
import com.example.trackfit.model.Meal
import kotlinx.coroutines.flow.Flow

interface StorageService {
    // Activity
    val activities: Flow<List<Activity>>
    suspend fun getActivity(activityId: String): Activity?
    suspend fun saveActivity(activity: Activity): String
    suspend fun updateActivity(activity: Activity)
    suspend fun deleteActivity(activityId: String)

    // Meal
    val meals: Flow<List<Meal>>
    suspend fun getMeal(mealId: String): Meal?
    suspend fun saveMeal(meal: Meal): String
    suspend fun updateMeal(meal: Meal)
    suspend fun deleteMeal(mealId: String)
}
