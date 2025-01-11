package com.example.trackfit.ui.screens.bmicalculator

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.trackfit.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculatorScreen(
    navController: NavHostController
) {
   // var age by remember { mutableStateOf("") }
    var mExpanded by remember { mutableStateOf(false) }

    val age = (1..100).toList()
    var mSelectedAge by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    //var gender by remember { mutableStateOf("") }

    var mExpandedGender by remember { mutableStateOf(false) }
    val gender = listOf("Male","Female")

    var mSelectedGender by remember { mutableStateOf("") }
    var mGenderFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon2 = if (mExpandedGender)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var height by remember { mutableStateOf("") }
    val cm = height.toDoubleOrNull() ?: 0.0
    var weight by remember { mutableStateOf("") }
    val kgs = weight.toDoubleOrNull() ?: 0.0
    var bmiResult by remember { mutableStateOf("") }
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5FB1B7), Color(0xFF8E9A9B))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5FB1B7),

                )


            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(gradient)
        ) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 40.dp)
                    .verticalScroll(rememberScrollState())
                    .safeDrawingPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.bmicalculator),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 40.dp)
                        .align(alignment = Alignment.CenterHorizontally),

                    )

                OutlinedTextField(
                    value = mSelectedAge,
                    onValueChange = { mSelectedAge = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Age") },
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )
                DropdownMenu(
                    expanded = mExpanded,
                    onDismissRequest = { mExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                ) {
                    age.forEach { age ->
                        DropdownMenuItem(
                            text = { Text(age.toString()) },
                            onClick = {
                                mSelectedAge = age.toString()
                                mExpanded = false
                            }
                        )
                    }
                }

                OutlinedTextField(
                    value = mSelectedGender,
                    onValueChange = { mSelectedGender = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            // This value is used to assign to
                            // the DropDown the same width
                            mGenderFieldSize = coordinates.size.toSize()
                        },
                    label = { Text("Gender") },
                    trailingIcon = {
                        Icon(icon2, "Gender",
                            Modifier.clickable { mExpandedGender = !mExpandedGender })
                    }
                )
                DropdownMenu(
                    expanded = mExpandedGender,
                    onDismissRequest = { mExpandedGender = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { mGenderFieldSize.width.toDp() })
                        .padding(bottom = 500.dp, top = 25.dp)
                ) {
                    gender.forEach { gender ->
                        DropdownMenuItem(
                            text = { Text(text = gender) },
                            onClick = {
                                mSelectedGender = gender
                                mExpandedGender = false
                            }
                        )
                    }
                }

                EditHeightField(
                    label = R.string.height,
                    value = height,
                    onValueChanged = { height = it },
                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 8.dp)
                        .fillMaxWidth(),

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
                        .padding(bottom = 32.dp, top = 8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )

                Button(
                    onClick = {
                        bmiResult = calcbmi(cm, kgs).toString()
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
    OutlinedTextField(

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
    OutlinedTextField(

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
    BmiCalculatorScreen(
        navController = rememberNavController()
    )
}