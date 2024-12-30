package com.example.trackfit.data.activity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val duration: Int,
    val date: Long
)