package com.example.trackfit.ui.screens.nutrigo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackfit.model.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriGoScreen(
    navigateBack: () -> Unit,
    openScreen: (String) -> Unit,
    viewModel: NutriGoViewModel = hiltViewModel()
) {
    val meals = viewModel.meals.collectAsStateWithLifecycle(emptyList())
    val totalCalories by viewModel.totalCalories.collectAsState()

    NutriGoScreenContent(
        meals = meals.value,
        totalCalories = totalCalories,
        onAddClick = { viewModel.onAddClick(openScreen) },
        onBackClick = { viewModel.onBackClick(navigateBack) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutriGoScreenContent(
    meals: List<Meal>,
    totalCalories: Int = 0,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    if (totalCalories == 3000) {
        Toast.makeText(context, "Congrats! You've hit your calorie goal!", Toast.LENGTH_SHORT)
            .show()
    } else if (totalCalories >= 3000) {
        Toast.makeText(context, "You've exceed your daily calorie!", Toast.LENGTH_SHORT).show()
    }
    val gradient = Brush.verticalGradient(colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B)))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nutri-Go") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5FB1B7),
                    titleContentColor = Color.Black,
                )

            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gradient),

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
                    progress = totalCalories / 3000f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = Color(0xFFD32F2F),
                    trackColor = Color(0xFFE0E0E0)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$totalCalories kcal / 3000 kcal",
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
                meals.forEach { meal ->
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
                            Text(
                                text = meal.category,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = meal.name, fontSize = 14.sp, color = Color.Gray)
                            Text(
                                text = "${meal.calories} kcal",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
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
                    onClick = onAddClick,
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
    NutriGoScreenContent(
        meals = listOf(
            Meal(name = "Pizza", category = "Lunch", calories = 200),
            Meal(name = "Salad", category = "Breakfast", calories = 150)
        ),
        totalCalories = 500,
        onAddClick = {},
        onBackClick = {}
    )
}