package com.example.trackfit.ui.screens.nutrigo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriGoScreen(
    navController: NavController,
    viewModel: NutriGoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val mealLog by viewModel.mealLog.collectAsState()
    val totalCalories by viewModel.totalCalories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nutri-Go") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        @Suppress("DEPRECATION")
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
                @Suppress("DEPRECATION")
                LinearProgressIndicator(
                    progress = totalCalories / 2000f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color(0xFFD32F2F),
                    trackColor = Color(0xFFE0E0E0)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$totalCalories kcal / 2000 kcal",
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
                mealLog.forEach { meal ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = meal.category, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = meal.name, fontSize = 14.sp, color = Color.Gray)
                            Text(text = "${meal.calories} kcal", fontSize = 14.sp, color = Color.Gray)
                        }
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
                    onClick = { navController.navigate(Routes.ADD_MEAL) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00123C),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Add Meal")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNutriGoScreen() {
    NutriGoScreen(navController = rememberNavController())
}