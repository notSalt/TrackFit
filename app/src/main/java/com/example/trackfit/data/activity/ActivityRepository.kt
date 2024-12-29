package com.example.trackfit.data.activity

import kotlinx.coroutines.flow.Flow

interface ActivityRepository {
    fun getAllActivitiesStream(): Flow<List<Activity>>

    fun getActivityStream(id: Int): Flow<Activity?>

    suspend fun insertActivity(activity: Activity)

    suspend fun deleteActivity(activity: Activity)

    suspend fun updateActivity(activity: Activity)
}