package com.example.trackfit.ui.screens.activitylog

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.R
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.utils.Routes
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityScreen(
    navController: NavController,
    viewModel: AddActivityViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        @Suppress("DEPRECATION")
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
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
                .background(gradient)
        ) {
            AddActivityBody(
                navController = navController,
                activityUiState = viewModel.activityUiState,
                onActivityValueChange = viewModel::updateUiState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveActivity()
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}

@Composable
fun AddActivityBody(
    navController: NavController,
    activityUiState: ActivityUiState,
    onActivityValueChange: (ActivityDetails) -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.addActivity),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 60.dp)
                .align(alignment = Alignment.CenterHorizontally),
        )

        ActivityInputForm(
            activityDetails = activityUiState.activityDetails,
            onValueChange = onActivityValueChange
        )

        DateTimePickerButton(
            currentDateTime = activityUiState.activityDetails.date,
            onDateTimeSelected = { newDateTimeInMillis ->
                onActivityValueChange(activityUiState.activityDetails.copy(date = newDateTimeInMillis))
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 150.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onSaveClick,
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
                onClick = { navController.navigate(Routes.ACTIVITY_LOG) },
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

@Composable
fun ActivityInputForm(
    activityDetails: ActivityDetails,
    modifier: Modifier = Modifier,
    onValueChange: (ActivityDetails) -> Unit = {}
) {
    EditActivityField(
        label = R.string.activity,
        value = activityDetails.name,
        onValueChanged = { onValueChange(activityDetails.copy(name = it)) },
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
    EditDurationField(
        label = R.string.duration,
        value = activityDetails.duration,
        onValueChanged = { onValueChange(activityDetails.copy(duration = it)) },
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )

}

@Composable
fun EditActivityField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {

    OutlinedTextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun EditDurationField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {

    OutlinedTextField(

        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
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
        "${date.get(Calendar.YEAR)}-${date.get(Calendar.MONTH) + 1}-${date.get(Calendar.DAY_OF_MONTH)} ${date.get(Calendar.HOUR_OF_DAY)}:${date.get(Calendar.MINUTE)}"
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
fun AddActivityPreview() {

    AddActivityScreen(navController = rememberNavController())

}
