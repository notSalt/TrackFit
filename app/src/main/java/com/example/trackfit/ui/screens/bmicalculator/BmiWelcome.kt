package com.example.trackfit.ui.screens.bmicalculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.R
import com.example.trackfit.ui.screens.nutrigo.NutriGoScreen
import com.example.trackfit.utils.Routes


@Composable
fun BmiWelcome(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val image = painterResource(R.drawable.bmiscreen3)
    Box(modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.9F,

        )

        IconButton(
            onClick = {
                navController.navigate(Routes.BMI_CALCULATOR)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ){
            Icon(
                imageVector = Icons.Default.ArrowForward,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "ToBmiCalculator"
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
fun BmiWelcomePreview() {
    BmiWelcome(navController = rememberNavController())
}