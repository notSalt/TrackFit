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
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trackfit.R
import com.example.trackfit.common.composable.EmailField
import com.example.trackfit.common.composable.PasswordField
import com.example.trackfit.common.composable.RepeatPasswordField
import com.example.trackfit.ui.theme.TrackFitTheme

@Composable
fun RegisterScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    RegisterScreenContent(
        uiState = uiState,
        onFirstNameChange = viewModel::onFirstNameChange,
        onLastNameChange = viewModel::onLastNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onRegisterClick = { viewModel.onRegisterClick(openAndPopUp) },
        onAlreadyRegistered = { viewModel.onAlreadyRegistered(openAndPopUp) }

    )
}

@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onAlreadyRegistered: () -> Unit,
) {
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
            value = uiState.firstName,
            onValueChange = onFirstNameChange,
            leadingIcon = { Icon(Icons.Rounded.Person, null) },
            label = { Text(stringResource(R.string.first_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = uiState.lastName,
            onValueChange = onLastNameChange,
            leadingIcon = { Icon(Icons.Rounded.Person, null) },
            label = { Text(stringResource(R.string.last_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        EmailField(
            value = uiState.email,
            onNewValue = onEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        PasswordField(
            value = uiState.password,
            onNewValue = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        RepeatPasswordField(
            value = uiState.repeatPassword,
            onNewValue = onRepeatPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onRegisterClick() },
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
                        linkInteractionListener = { onAlreadyRegistered() }
                    )
                ) { append("Login") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val uiState = RegisterUiState(
        email = "email@test.com"
    )

    TrackFitTheme {
        RegisterScreenContent(
            uiState = uiState,
            onEmailChange = { },
            onPasswordChange = { },
            onFirstNameChange = { },
            onLastNameChange = { },
            onRepeatPasswordChange = { },
            onRegisterClick = { },
            onAlreadyRegistered = { }
        )
    }
}