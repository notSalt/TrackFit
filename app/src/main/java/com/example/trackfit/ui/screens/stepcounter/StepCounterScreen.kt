package com.example.trackfit.ui.screens.stepcounter

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trackfit.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepCounterScreen(
    navigateBack: () -> Unit
) {
    var stepsToday by remember { mutableStateOf(0) }
    var goal by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var goalInput by remember { mutableStateOf(" ") }
    rememberCoroutineScope()
    val context = LocalContext.current
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )
    LaunchedEffect(isRunning, stepsToday) {
        if (isRunning && goal > 0 && stepsToday <= goal) {
            while (isRunning) {
                delay(1000L)
                stepsToday += 1
                if (stepsToday >= goal) {
                    stepsToday = goal
                    Toast.makeText(
                        context,
                        "Congrats! You've hit your steps goal!",
                        Toast.LENGTH_SHORT
                    ).show()
                    break
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Step Counter") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5FB1B7),
                    titleContentColor = Color.Black,
                ),
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gradient)
        ) {
            Text(
                text = "Steps Today: $stepsToday",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { if (goal > 0) stepsToday.toFloat() / goal.toFloat() else 0f },
                color = Color.Red,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (goal > 0) "${(stepsToday.toFloat() / goal.toFloat() * 100).toInt()}% completed" else "no progress...",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column {

                EditGoalField(
                    label = R.string.editGoal,
                    value = goalInput,
                    onValueChanged = { goalInput = it },
                    modifier = Modifier
                        .padding(bottom = 32.dp),
                    //.fillMaxWidth()
                    //.clip(MaterialTheme.shapes.medium),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
                Text(
                    text = "GOAL: ${if (goal > 0) "$goal steps" else "Set Your Goal"}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }


            Spacer(modifier = Modifier.height(32.dp))

            Column {
                Row {
                    Button(
                        onClick = {
                            stepsToday = 0
                            goal = 0
                            isRunning = false
                            goalInput = ""
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("RESET")
                    }

                    Button(
                        onClick = {
                            goal = goalInput.toFloat().toInt()
                            goalInput = ""
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("SET GOAL")
                    }
                }

                Row {
                    Button(
                        onClick = { isRunning = true },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("START")
                    }

                    Button(
                        onClick = {
                            isRunning = false
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("STOP")
                    }
                }
            }
        }
    }
}


@Composable
fun EditGoalField(
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


@Preview(showBackground = true)
@Composable
fun PreviewStepCounterScreen() {
    StepCounterScreen(
        navigateBack = { }
    )
}