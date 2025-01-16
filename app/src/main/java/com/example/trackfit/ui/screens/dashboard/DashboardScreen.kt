package com.example.trackfit.ui.screens.dashboard

//import android.R

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.LoginStateManager
import com.example.trackfit.R


import com.example.trackfit.ui.theme.TrackFitTheme
import com.example.trackfit.utils.Routes


@Composable
fun DashboardScreen(
    navController: NavHostController,
    context: Context,


) {

        val loginStateManager = remember { LoginStateManager(context) }
        val fullName = loginStateManager.getUserFullName() ?: "User"
        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
        )

        Column (

            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
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
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp,

                ),
               colors = CardDefaults.cardColors(
                   containerColor = Color.Transparent
               ),
                modifier = Modifier
                    .size(width = 400.dp, height = 450.dp)
            ) {
               Column (
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
                                   .clickable {
                                       navController.navigate(Routes.BMI_WELCOME)
                                   }
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
                                   .clickable {
                                       navController.navigate(Routes.WORKOUT_WELCOME)
                                   }
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
                                   .clickable {
                                       navController.navigate(Routes.WATER_INTAKE_WELCOME)
                                   }
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
                                   .clickable {
                                       navController.navigate(Routes.STEP_COUNTER_WELCOME)
                                   }
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
                                   .clickable {
                                       navController.navigate(Routes.NUTRI_GO_WELCOME)
                                   }
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
                                   .clickable {
                                       navController.navigate(Routes.ACTIVITY_WELCOME)
                                   }
                           )
                           Text(
                               text = "Activity Log",
                               fontSize = 16.sp
                           )
                       }



                   }


               }
            }

            /**OptionButton(
                optionName = "BMI Calculator",
                onClick = { navController.navigate(Routes.BMI_WELCOME) }
            )
            OptionButton(
                optionName = "Workout Guide",
                onClick = { navController.navigate(Routes.WORKOUT_WELCOME) }
            )
            OptionButton(
                optionName = "Step Counter",
                onClick = { navController.navigate(Routes.STEP_COUNTER_WELCOME) }
            )
            OptionButton(
                optionName = "Activity Log",
                onClick = { navController.navigate(Routes.ACTIVITY_WELCOME) }
            )
            OptionButton(
                optionName = "Daily Water Tracker",
                onClick = { navController.navigate(Routes.WATER_INTAKE_WELCOME) }
            )
            OptionButton(
                optionName = "Nutri-Go",
                onClick = { navController.navigate(Routes.NUTRI_GO_WELCOME) }
            )**/


            // Logout Button
            /**Text(
                buildAnnotatedString {
                    withLink(
                        LinkAnnotation.Url(
                            url = "",
                            styles = TextLinkStyles(
                                style = SpanStyle(color = Color.Red)
                            ),
                            linkInteractionListener = {
                                loginStateManager.logOut()
                                navController.popBackStack(Routes.WELCOME, false)
                            }
                        )
                    ) { append("Logout") }
                }
            )**/
            Spacer(Modifier.height(50.dp))
            Button(
                onClick = {
                    loginStateManager.logOut()
                    navController.popBackStack(Routes.WELCOME, false)
                },
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
        DashboardScreen(
            navController = rememberNavController(),
            context = LocalContext.current
        )
    }
}