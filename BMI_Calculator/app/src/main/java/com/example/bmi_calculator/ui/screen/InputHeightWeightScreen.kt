package com.example.bmi_calculator.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bmi_calculator.route.BMICalRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputHeightWeightScreen(navHostController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BMICalOutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                },
                labelText = "키"
            )

            BMICalOutlinedTextField(
                value = weight,
                onValueChange = {
                    weight = it
                },
                labelText = "몸무게"
            )

            Button(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 12.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    if (height.isNotBlank() && weight.isNotBlank()) {
                        navHostController.navigate(
                            BMICalRoute.BMIResultRoute.createRoute(
                                height.toFloat(),
                                weight.toFloat()
                            )
                        )
                        height = ""
                        weight = ""
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "키와 몸무게를 입력해주세요.",
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }
                }
            ) {
                Text(text = "결과", fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BMICalOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = labelText)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}