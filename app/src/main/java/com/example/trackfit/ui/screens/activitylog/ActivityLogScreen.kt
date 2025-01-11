package com.example.trackfit.ui.screens.activitylog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.utils.Routes
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Brush
import com.example.trackfit.data.activity.Activity
import java.text.DateFormat.getDateTimeInstance
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityLogScreen(
    navController: NavHostController,
    viewModel: ActivityLogViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val activityLogUiState by viewModel.activityLogUiState.collectAsState()
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Activity Log") },
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
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(Routes.ADD_ACTIVITY) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00123C), // Dark blue
                    contentColor = Color.White
                )
            ) {
                Text(text = "Add Activity")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(items = activityLogUiState.activityList, key = { it.id }) { activity ->
                    ActivityCard(activity = activity)
                }
            }
        }
        
    }
}

@Composable
fun ActivityCard(activity: Activity) {
    ElevatedCard(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = activity.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = "${activity.duration} minutes",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = convertLongToTime(activity.date),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = getDateTimeInstance()
    return format.format(date)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityLogScreen(
        navController = rememberNavController()
    )
}