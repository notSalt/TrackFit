package com.example.trackfit.ui.screens.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.LoginStateManager
import com.example.trackfit.R
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.ui.theme.TrackFitTheme
import com.example.trackfit.utils.Routes

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory),
    context: Context
) {
    val loginStateManager = remember { LoginStateManager(context) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var loginFailed by remember { mutableStateOf(false) }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Hey there,",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
            ),
        )
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = { Icon(Icons.Rounded.Email, null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            leadingIcon = { Icon(Icons.Rounded.Lock, null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                isLoading = true
                viewModel.loginUser(email, password) { success, user ->
                    isLoading = false
                    if (success) {
                        loginStateManager.logIn(user?.firstName + " " + user?.lastName)
                        navController.navigate(Routes.DASHBOARD)
                    } else {
                        loginFailed = true
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(56.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(50),
                    clip = false
                )
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (loginFailed) {
            Text(
                text = "Login Failed. Please check your credentials.",
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                append("Don't have an account yet? ")
                withLink(
                    LinkAnnotation.Url(
                        url = "",
                        styles = TextLinkStyles(
                            style = SpanStyle(color = Color.Blue),
                            hoveredStyle = SpanStyle(color = Color.Red)
                        ),
                        linkInteractionListener = { navController.navigate(Routes.REGISTER) }
                    )
                ) { append("Register") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TrackFitTheme {
        LoginScreen(
            navController = rememberNavController(),
            context = LocalContext.current
        )
    }
}