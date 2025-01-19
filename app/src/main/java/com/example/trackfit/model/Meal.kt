package com.example.trackfit.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Meal(
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val name: String = "",
    val category: String = "",
    val calories: Int = 0,
    val userId: String = ""
)