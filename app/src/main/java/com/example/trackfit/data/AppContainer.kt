package com.example.trackfit.data

import com.example.trackfit.data.activity.ActivityRepository
import android.content.Context
import com.example.trackfit.data.activity.OfflineActivityRepository
import com.example.trackfit.data.meal.MealRepository
import com.example.trackfit.data.meal.OfflineMealRepository
import com.example.trackfit.data.user.OfflineUserRepository
import com.example.trackfit.data.user.UserRepository

interface AppContainer {
    val activityRepository: ActivityRepository
    val mealRepository: MealRepository
    val userRepository: UserRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val activityRepository: ActivityRepository by lazy {
        OfflineActivityRepository(TrackFitDatabase.getDatabase(context).activityDao())
    }

    override val mealRepository: MealRepository by lazy {
        OfflineMealRepository(TrackFitDatabase.getDatabase(context).mealDao())
    }

    override val userRepository: UserRepository by lazy {
        OfflineUserRepository(TrackFitDatabase.getDatabase(context).userDao())
    }
}