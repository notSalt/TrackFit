package com.example.trackfit.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Activity(
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val name: String = "",
    val duration: Int = 0,
    val datetime: String = "",
    val userId: String = ""
)