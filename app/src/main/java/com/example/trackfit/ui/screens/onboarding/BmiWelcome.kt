package com.example.trackfit.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trackfit.R


@Composable
fun BmiWelcome(
    pressBack: () -> Unit,
    pressForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.bmiscreen3)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.9F,

            )

        IconButton(
            onClick = { pressForward() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                modifier = Modifier
                    .size(50.dp),
                contentDescription = "ToBmiCalculator"
            )
        }
        IconButton(
            onClick = { pressBack() },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)

        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
    BmiWelcome(
        pressBack = {},
        pressForward = {}
    )
}