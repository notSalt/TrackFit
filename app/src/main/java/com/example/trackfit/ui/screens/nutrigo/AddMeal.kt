package com.example.trackfit.ui.screens.nutrigo

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.R
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.ui.screens.activitylog.AddActivityViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMealScreen(
    navController: NavController,
    viewModel: AddMealViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val mealUiState = viewModel.mealUiState

    var mExpanded by remember { mutableStateOf(false) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5FB1B7),
                    titleContentColor = Color.Black,
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
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
                    text = stringResource(R.string.addMeal),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(bottom = 50.dp, top = 60.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                )

                // Dropdown for Category
                OutlinedTextField(
                    value = mealUiState.mealDetails.category,
                    onValueChange = {
                        viewModel.updateUiState(
                            mealUiState.mealDetails.copy(category = it)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Category") },
                    trailingIcon = {
                        Icon(
                            icon, "contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    listOf("Breakfast", "Lunch", "Dinner").forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = label) },
                            onClick = {
                                viewModel.updateUiState(
                                    mealUiState.mealDetails.copy(category = label)
                                )
                                mExpanded = false
                            }
                        )
                    }
                }

                // Input for Meal Name
                EditMealField(
                    label = R.string.meal,
                    value = mealUiState.mealDetails.name,
                    onValueChanged = {
                        viewModel.updateUiState(
                            mealUiState.mealDetails.copy(name = it)
                        )
                    },
                    modifier = Modifier
                        .padding(bottom = 32.dp, top = 25.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                // Input for Calorie Count
                EditCalorieField(
                    label = R.string.calorie,
                    value = mealUiState.mealDetails.calories.toString(),
                    onValueChanged = {
                        val calories = it.toIntOrNull() ?: 0
                        viewModel.updateUiState(
                            mealUiState.mealDetails.copy(calories = calories)
                        )
                    },
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )

                // Action Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 150.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.saveMeal()
                                navController.navigateUp() // Navigate back after saving
                            }
                        },
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .weight(2f)
                    ) {
                        Text(
                            text = "Add",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = { navController.navigateUp() },
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
}


@Composable
fun EditMealField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
){

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
fun EditCalorieField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
){

    OutlinedTextField(

        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
}



@Preview
@Composable
fun AddMealPreview() {

    AddMealScreen(navController = rememberNavController())

}