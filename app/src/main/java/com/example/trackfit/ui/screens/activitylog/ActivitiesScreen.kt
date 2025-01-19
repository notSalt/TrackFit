package com.example.trackfit.ui.screens.activitylog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackfit.model.Activity
import com.example.trackfit.ui.theme.TrackFitTheme

@Composable
@ExperimentalMaterial3Api
fun ActivitiesScreen(
    navigateBack: () -> Unit,
    openScreen: (String) -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    val activities = viewModel.activities.collectAsStateWithLifecycle(emptyList())

    ActivitiesScreenContent(
        activities = activities.value,
        onBackClick = { viewModel.onBackClick(navigateBack) },
        onAddClick = { viewModel.onAddClick(openScreen) },
    )
}

@Composable
@ExperimentalMaterial3Api
fun ActivitiesScreenContent(
    modifier: Modifier = Modifier,
    activities: List<Activity>,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Activity Log") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick() },
                shape = RoundedCornerShape(12.dp),
                contentColor = Color.White,
                containerColor = Color(0xFF00123C),
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Icon(Icons.Filled.Add, "Add Activity")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
                    )
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(items = activities, key = { it.id }) { activity ->
                    ActivityCard(activity = activity)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun DefaultPreview() {
    val activity = Activity(
        name = "Jogging",
        duration = 30,
        datetime = "01/01/2025 06:00"
    )
    TrackFitTheme {
        ActivitiesScreenContent(
            activities = listOf(activity),
            onBackClick = { },
            onAddClick = { },
        )
    }
}