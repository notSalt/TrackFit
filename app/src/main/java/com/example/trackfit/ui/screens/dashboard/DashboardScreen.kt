package com.example.trackfit.ui.screens.dashboard

import android.R
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.LoginStateManager
import com.example.trackfit.ui.theme.TrackFitTheme
import com.example.trackfit.utils.Routes

@Composable
fun DashboardScreen(
    navController: NavHostController,
    context: Context
) {
    val loginStateManager = remember { LoginStateManager(context) }
    val fullName = loginStateManager.getUserFullName() ?: "User"

        Column (

            modifier = Modifier.padding(16.dp) ,
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
                        painter = painterResource(id = R.drawable.ic_menu_camera), // Placeholder icon
                        contentDescription = "User Profile",
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = "Hi, $fullName",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            OptionButton(
                optionName = "BMI Calculator",
                onClick = { navController.navigate(Routes.BMI_CALCULATOR) }
            )
            OptionButton(
                optionName = "Workout Guide",
                onClick = { navController.navigate(Routes.WORKOUT_GUIDE) }
            )
            OptionButton(
                optionName = "Step Counter",
                onClick = { navController.navigate(Routes.STEP_COUNTER) }
            )
            OptionButton(
                optionName = "Activity Log",
                onClick = { navController.navigate(Routes.ACTIVITY_LOG) }
            )
            OptionButton(
                optionName = "Daily Water Tracker",
                onClick = { navController.navigate(Routes.WATER_INTAKE) }
            )
            OptionButton(
                optionName = "Nutri-Go",
                onClick = { navController.navigate(Routes.NUTRI_GO) }
            )

            // Logout Button
            Text(
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
            )
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