package com.example.bmi_calculator.route

sealed class BMICalRoute(val route: String) {
    object InputHeightWeightRoute: BMICalRoute("inputHeightWeight")
    object BMIResultRoute: BMICalRoute("bmiResult/{$HEIGHT}/{$WEIGHT}") {
        fun createRoute(height: Float, weight: Float): String = "bmiResult/$height/$weight"
    }

    companion object {
        const val HEIGHT: String = "height"
        const val WEIGHT: String = "weight"
    }
}

