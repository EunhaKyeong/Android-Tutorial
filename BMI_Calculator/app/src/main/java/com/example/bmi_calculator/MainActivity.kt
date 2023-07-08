package com.example.bmi_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.bmi_calculator.navHost.BMICalculatorNavHost
import com.example.bmi_calculator.route.BMICalRoute
import com.example.bmi_calculator.ui.theme.BMI_CalculatorTheme
import com.example.bmi_calculator.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMI_CalculatorTheme {
                val navController = rememberNavController()
                var navControllerDestination by rememberSaveable { mutableStateOf(BMICalRoute.InputHeightWeightRoute.route) }

                navController.addOnDestinationChangedListener { controller, destination, arguments ->
                    navControllerDestination =
                        destination.route ?: BMICalRoute.InputHeightWeightRoute.route
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        BMICalTopBar(
                            navigationIcon = if (navControllerDestination == BMICalRoute.BMIResultRoute.route) {
                                {
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            Icons.Filled.ArrowBack,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                }
                            } else {
                                {}
                            }
                        )
                    }
                ) { paddingValues ->
                    BMICalculatorNavHost(
                        navHostController = navController,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalTopBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = "비만도 계산기", color = Color.White) },
        modifier = modifier,
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple40)
    )
}