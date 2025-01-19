package com.example.trackfit.ui.screens.dashboard

//import android.R


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.trackfit.R
import com.example.trackfit.model.User
import com.example.trackfit.ui.theme.TrackFitTheme


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    clearAndNavigate: (String) -> Unit,
    openScreen: (String) -> Unit,
) {
    val user = viewModel.currentUser.collectAsStateWithLifecycle(User())
    val fullName = user.value.firstName + " " + user.value.lastName

    DashboardScreenContent(
        fullName = fullName,
        openBMICalculator = { viewModel.openBMICalculator(openScreen) },
        openWorkoutGuide = { viewModel.openWorkoutGuide(openScreen) },
        openDailyWaterIntake = { viewModel.openDailyWaterIntake(openScreen) },
        openStepCounter = { viewModel.openStepCounter(openScreen) },
        openNutriGo = { viewModel.openNutriGo(openScreen) },
        openActivityLog = { viewModel.openActivityLog(openScreen) },
        onLogoutClick = { viewModel.onLogoutClick(clearAndNavigate) }
    )
}

@Composable
fun DashboardScreenContent(
    fullName: String = "User",
    openBMICalculator: () -> Unit,
    openWorkoutGuide: () -> Unit,
    openDailyWaterIntake: () -> Unit,
    openStepCounter: () -> Unit,
    openNutriGo: () -> Unit,
    openActivityLog: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
                )
            )
            .padding(0.dp, 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dashboard",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic), // Placeholder icon
                    contentDescription = "User Profile",
                    modifier = Modifier.size(80.dp)
                )
            }
            Text(
                text = "Hi, $fullName",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier.size(width = 380.dp, height = 450.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.bmi_calc),
                            contentDescription = "bmicalcIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable { openBMICalculator() }
                        )
                        Text(
                            text = "BMI Calculator",
                            fontSize = 16.sp
                        )
                    }



                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.workouticonresize),
                            contentDescription = "workoutIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable { openWorkoutGuide() }
                        )
                        Text(
                            text = "Workout Guide",
                            fontSize = 16.sp
                        )
                    }


                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.watericonresize),
                            contentDescription = "WaterIntIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .padding(end = 10.dp)
                                .size(50.dp)
                                .clickable { openDailyWaterIntake() }
                        )
                        Text(
                            text = "Daily Water Intake",
                            fontSize = 16.sp
                        )
                    }

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.stepicon2),
                            contentDescription = "stepCounterIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable { openStepCounter() }
                        )
                        Text(
                            text = "Step Counter",
                            fontSize = 16.sp
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.nutrigoicon2),
                            contentDescription = "NutriGoIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable { openNutriGo() }
                        )
                        Text(
                            text = "NutriGo",
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(38.dp))

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.activity_log),
                            contentDescription = "activityLogIcon",
                            modifier = Modifier
                                .padding(8.dp)
                                .size(50.dp)
                                .clickable { openActivityLog() }
                        )
                        Text(
                            text = "Activity Log",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(50.dp))

        // Logout Button
        Button(
            onClick = { onLogoutClick() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(text = "Logout")
        }
    }
}


@Composable
fun OptionButton(
    optionName: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = optionName,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrackFitPreview() {
    TrackFitTheme {
        DashboardScreenContent(
            fullName = "John Doe",
            openActivityLog = { },
            openBMICalculator = { },
            openDailyWaterIntake = { },
            openNutriGo = { },
            openStepCounter = { },
            openWorkoutGuide = { },
            onLogoutClick = { }
        )
    }
}