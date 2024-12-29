package com.example.trackfit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trackfit.data.activity.Activity
import com.example.trackfit.data.activity.ActivityDao
import com.example.trackfit.data.meal.Meal
import com.example.trackfit.data.meal.MealDao
import com.example.trackfit.data.user.User
import com.example.trackfit.data.user.UserDao

@Database(entities = [Activity::class, Meal::class, User::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TrackFitDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun mealDao(): MealDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: TrackFitDatabase? = null

        fun getDatabase(context: Context): TrackFitDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TrackFitDatabase::class.java, "trackfit_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}