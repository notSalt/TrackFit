package com.example.trackfit.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.example.trackfit.R
import com.example.trackfit.ui.AppViewModelProvider
import com.example.trackfit.ui.screens.login.LoginViewModel
import com.example.trackfit.ui.theme.TrackFitTheme
import com.example.trackfit.utils.Routes

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var registerFailed by remember { mutableStateOf(false) }
    var reasonFailed by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            text = "Create an Account",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(8.dp)
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            leadingIcon = { Icon(Icons.Rounded.Person, null) },
            label = { Text(stringResource(R.string.first_name)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            leadingIcon = { Icon(Icons.Rounded.Person, null) },
            label = { Text(stringResource(R.string.last_name)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = { Icon(Icons.Rounded.Email, null) },
            label = { Text(stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = { Icon(Icons.Rounded.Lock, null) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                isLoading = true
                viewModel.registerUser(firstName, lastName, email, password) { success, reason ->
                    isLoading = false
                    if (success) {
                        navController.navigate(Routes.LOGIN)
                    } else {
                        registerFailed = true
                        reasonFailed = reason
                    }
                } },
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
                text = "Register",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (registerFailed) {
            Text(
                text = reasonFailed,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                append("Already have an account? ")
                withLink(
                    LinkAnnotation.Url(
                        url = "",
                        styles = TextLinkStyles(
                            style = SpanStyle(color = Color.Blue),
                            hoveredStyle = SpanStyle(color = Color.Red)
                        ),
                        linkInteractionListener = { navController.navigate(Routes.LOGIN) }
                    )
               ) { append("Login") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    TrackFitTheme {
        RegisterScreen(
            navController = rememberNavController()
        )
    }
}