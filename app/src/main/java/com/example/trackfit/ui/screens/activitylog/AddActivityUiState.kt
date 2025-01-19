package com.example.trackfit.ui.screens.activitylog

import com.example.trackfit.common.ext.fromDateToLong
import com.example.trackfit.common.ext.toDateTimeString
import com.example.trackfit.model.Activity

data class AddActivityUiState(
    val id: String = "",
    val name: String = "",
    val duration: String = "",
    val datetime: Long = 0L
)

fun AddActivityUiState.toActivity(): Activity = Activity(
    id = id,
    name = name,
    duration = duration.toInt(),
    datetime = datetime.toDateTimeString()
)

fun Activity.toAddActivityUiState(): AddActivityUiState = AddActivityUiState(
    id = id,
    name = name,
    duration = duration.toString(),
    datetime = datetime.fromDateToLong()
)