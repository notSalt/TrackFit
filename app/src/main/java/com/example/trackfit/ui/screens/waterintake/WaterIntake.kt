package com.example.trackfit.ui.screens.waterintake

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WaterIntakeScreen(
    navigateBack: () -> Unit
) {
    var currentWaterIntake by remember { mutableStateOf(0) }
    var goal by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Daily Water Intake") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
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
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 40.dp)
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "${currentWaterIntake}ml    /    ${goal}ml",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(top = 80.dp)
                        .align(alignment = Alignment.CenterHorizontally)

                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 200.dp),

                    ) {

                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Increase 100ml",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(
                                enabled = currentWaterIntake < goal
                            ) {
                                currentWaterIntake += 100
                                if (currentWaterIntake >= goal) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Congrats! You've hit your water goal for the day!",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()

                                }


                            }
                    )
                    Text(
                        text = "100ml",
                        color = Color.Black,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 0.5.dp, top = 4.dp, end = 20.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Increase 200ml",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(
                                enabled = currentWaterIntake < goal
                            ) {
                                currentWaterIntake += 200
                                if (currentWaterIntake >= goal) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Congrats! You've hit your water goal for the day!",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()

                                }


                            }
                    )
                    Text(
                        text = "200ml",
                        color = Color.Black,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 0.5.dp, top = 4.dp)

                    )
                }




                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            goal = 2000
                        },
                        shape = RoundedCornerShape(100),

                        colors = ButtonDefaults.buttonColors(Color.Black),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            //.weight(3f)
                    ) {
                        Text(
                            text = "Set Goal",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            currentWaterIntake = 0
                            goal = 0
                        },
                        shape = RoundedCornerShape(100),
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            //.weight(3f)
                    ) {
                        Text(
                            text = "Reset",
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun WaterIntakePreview() {

    WaterIntakeScreen(
        navigateBack = { }
    )

}