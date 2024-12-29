package com.example.trackfit.ui.screens.activitylog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trackfit.data.activity.ActivityRepository
import com.example.trackfit.data.activity.Activity

class AddActivityViewModel(private val activitiesRepository: ActivityRepository) : ViewModel() {
    var activityUiState by mutableStateOf(ActivityUiState())
        private set

    fun updateUiState(activityDetails: ActivityDetails) {
        activityUiState =
            ActivityUiState(activityDetails = activityDetails, isEntryValid = validateInput(activityDetails))
    }

    suspend fun saveActivity() {
        if (validateInput()) {
            activitiesRepository.insertActivity(activityUiState.activityDetails.toActivity())
        }
    }

    private fun validateInput(uiState: ActivityDetails = activityUiState.activityDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && duration.isNotBlank() && date != 0L
        }
    }


}

data class ActivityUiState(
    val activityDetails: ActivityDetails = ActivityDetails(),
    val isEntryValid: Boolean = false
)

data class ActivityDetails(
    val id: Int = 0,
    val name: String = "",
    val duration: String = "",
    val date: Long = 0L,
)

fun ActivityDetails.toActivity(): Activity = Activity(
    id = id,
    name = name,
    duration = duration.toIntOrNull() ?: 0,
    date = date,
)

fun Activity.toActivityUiState(isEntryValid: Boolean = false): ActivityUiState = ActivityUiState(
    activityDetails = this.toActivityDetails(),
    isEntryValid = isEntryValid
)

fun Activity.toActivityDetails(): ActivityDetails = ActivityDetails(
    id = id,
    name = name,
    duration = duration.toString(),
    date = date
)