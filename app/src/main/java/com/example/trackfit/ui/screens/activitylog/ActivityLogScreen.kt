package com.example.trackfit.ui.screens.activitylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityLogScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Activity Log") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(sampleActivities) { activity ->
                ActivityCard(activity = activity)
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
                text = "${activity.duration} mins",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = activity.date,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

data class Activity(
    val name: String,
    val duration: Int,
    val date: String
)

val sampleActivities = listOf(
    Activity("Running", 30, "Dec 4, 2024, 6:00pm"),
    Activity("Walking", 20, "Dec 5, 2024, 6:00pm")
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityLogScreen(
        navController = rememberNavController()
    )
}