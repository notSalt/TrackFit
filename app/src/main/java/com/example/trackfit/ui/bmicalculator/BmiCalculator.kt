package com.example.trackfit.ui.bmicalculator

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trackfit.R


@Composable
fun BmiCalculatorScreen() {
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    val cm = height.toDoubleOrNull() ?: 0.0
    var weight by remember { mutableStateOf("") }
    val kgs = weight.toDoubleOrNull() ?: 0.0
    var bmiResult by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                text = stringResource(R.string.bmicalculator),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 40.dp)
                    .align(alignment = Alignment.CenterHorizontally),

            )

            EditAgeField(
                label = R.string.userAge,
                value = age,
                onValueChanged = { age = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            EditGenderField(
                label = R.string.gender,
                value = gender,
                onValueChanged = { gender = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            EditHeightField(
                label = R.string.height,
                value = height,
                onValueChanged = { height = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            EditWeightField(
                label = R.string.weight,
                value = weight,
                onValueChanged = { weight = it },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Button(
                onClick = {
                    bmiResult = calcbmi(cm,kgs).toString()
                },
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
            Text(
                text = "Result : " + bmiResult,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(top = 80.dp).align(alignment = Alignment.Start)
            )

        }

    }
}

@Composable
fun EditAgeField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
    ){
        TextField(

            value = value,
            singleLine = true,
            modifier = modifier,
            onValueChange = onValueChanged,
            label = { Text(stringResource(label)) },
            keyboardOptions = keyboardOptions,

        )

}

@Composable
fun EditGenderField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
){
    TextField(

        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )
}

@Composable
fun EditHeightField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
){
    TextField(

        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )

}

@Composable
fun EditWeightField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
){
    TextField(

        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
    )

}

private fun calcbmi(
    cm: Double,
    kgs : Double

): String {
    var heightInmeters= cm/100
    var status = ""
    var result = kgs/(heightInmeters*heightInmeters)
    var updatedResult = String.format("%.2f",result)
    if(result<=18.4){
        status = "Underweight"
    }
    else if (result>18.4 && result<=24.9){
        status = "Normal"
    }
    else if (result>24.9 && result<=39.9){
        status = "Overweight"
    }
    else if (result>39.9){
        status = "Obese"
    }
    return "$updatedResult ($status)"

}


@Preview
@Composable
fun BmiCalcPreview() {
    BmiCalculatorScreen()
}