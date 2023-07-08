package com.example.bmi_calculator.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bmi_calculator.route.BMICalRoute
import com.example.bmi_calculator.route.BMICalRoute.Companion.HEIGHT
import com.example.bmi_calculator.route.BMICalRoute.Companion.WEIGHT
import com.example.bmi_calculator.ui.screen.BMIResultScreen
import com.example.bmi_calculator.ui.screen.InputHeightWeightScreen

@Composable
fun BMICalculatorNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = BMICalRoute.InputHeightWeightRoute.route,
        modifier = modifier
    ) {
        composable(BMICalRoute.InputHeightWeightRoute.route) {
            InputHeightWeightScreen(navHostController)
        }

        composable(
            route = BMICalRoute.BMIResultRoute.route,
            arguments = listOf(
                navArgument(HEIGHT) { type = NavType.FloatType },
                navArgument(WEIGHT) { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val height = backStackEntry.arguments?.getFloat(HEIGHT) ?: 0f
            val weight = backStackEntry.arguments?.getFloat(WEIGHT) ?: 0f

            BMIResultScreen(height, weight)
        }
    }
}