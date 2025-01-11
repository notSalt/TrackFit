package com.example.trackfit.ui.screens.workoutguide

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.R
import com.example.trackfit.utils.Routes

@Composable
fun WorkoutWelcome(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val image = painterResource(R.drawable.workoutwelcome1)
    Box(modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.9F,

            )

        IconButton(
            onClick = {
                navController.navigate(Routes.WORKOUT_GUIDE)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ){
            Icon(
                imageVector = Icons.Default.ArrowForward,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "ToWorkoutGuide"
            )
        }
        IconButton(
            onClick = {
                navController.navigateUp()
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)

        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "ToDashboard"
            )
        }


    }

}


@Preview
@Composable
fun WorkoutWelcomePreview() {
    WorkoutWelcome(navController = rememberNavController())
}