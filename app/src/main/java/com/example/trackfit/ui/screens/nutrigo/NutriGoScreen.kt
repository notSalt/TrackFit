package com.example.trackfit.ui.screens.nutrigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriGoScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriGoScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nutri-Go") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Progress Bar Section
            Column(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = 0.75f, // 1500 / 2000
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color(0xFFD32F2F), // Red
                    trackColor = Color(0xFFE0E0E0) // Light gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "1500 kcal / 2000 kcal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Meal Log Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Meal Log",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Breakfast", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Bread Toast", fontSize = 14.sp, color = Color.Gray)
                        Text(text = "50 kcal", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }

            // Buttons Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { /* Handle Add Meal */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00123C), // Dark blue
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Add Meal")
                }

                Button(
                    onClick = { /* Handle Set Calorie Goal */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00123C),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Set Calorie Goal")
                }

                Button(
                    onClick = { /* Handle Reset */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00123C),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Reset")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNutriGoScreen() {
    NutriGoScreen()
}
