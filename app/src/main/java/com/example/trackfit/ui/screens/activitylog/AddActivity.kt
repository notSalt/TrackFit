package com.example.trackfit.ui.screens.activitylog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackfit.R
import com.example.trackfit.common.composable.BasicField
import com.example.trackfit.ui.theme.TrackFitTheme
import java.util.Calendar

@Composable
@ExperimentalMaterial3Api
fun AddActivityScreen(
    navigateBack: () -> Unit,
    viewModel: AddActivityViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    AddActivityContent(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onDurationChange = viewModel::onDurationChange,
        onDateTimeChange = viewModel::onDateTimeChange,
        onAddClick = { viewModel.onAddClick(navigateBack) },
        onBackClick = { viewModel.onBackClick(navigateBack) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@ExperimentalMaterial3Api
fun AddActivityContent(
    uiState: AddActivityUiState,
    onNameChange: (String) -> Unit,
    onDurationChange: (String) -> Unit,
    onDateTimeChange: (Long) -> Unit,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Activity") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Black,
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.addActivity),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 60.dp)
                    .align(alignment = Alignment.CenterHorizontally),
            )

            BasicField(R.string.activity, uiState.name, onNameChange)
            BasicField(R.string.duration, uiState.duration, onDurationChange)

            DateTimePickerButton(uiState.datetime, onDateTimeChange)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 150.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { onAddClick() },
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = "Save",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Button(
                    onClick = { onBackClick() },
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .weight(2f)
                ) {
                    Text(
                        text = "Cancel",
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun DateTimePickerButton(
    currentDateTime: Long,
    onDateTimeSelected: (Long) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Format the current timestamp to display as a formatted string
    val formattedDate = if (currentDateTime != 0L) {
        val date = Calendar.getInstance().apply { timeInMillis = currentDateTime }
        "${date.get(Calendar.YEAR)}-${date.get(Calendar.MONTH) + 1}-${date.get(Calendar.DAY_OF_MONTH)} ${
            date.get(
                Calendar.HOUR_OF_DAY
            )
        }:${date.get(Calendar.MINUTE)}"
    } else {
        "Select Date and Time"
    }

    Button(
        onClick = {
            // Launch DatePickerDialog first
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    // After date is selected, launch TimePickerDialog
                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            // Convert selected date and time to timestamp (milliseconds)
                            val selectedCalendar = Calendar.getInstance()
                            selectedCalendar.set(year, month, dayOfMonth, hourOfDay, minute, 0)
                            val selectedDateTimeInMillis = selectedCalendar.timeInMillis
                            onDateTimeSelected(selectedDateTimeInMillis)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        },
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {
        Text(
            text = formattedDate,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun AddActivityPreview() {
    TrackFitTheme {
        AddActivityContent(
            uiState = AddActivityUiState(),
            onNameChange = { },
            onDurationChange = { },
            onDateTimeChange = { },
            onBackClick = { },
            onAddClick = { }
        )
    }
}
