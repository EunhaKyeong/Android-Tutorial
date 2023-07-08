package com.example.bmi_calculator.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_calculator.R
import kotlin.math.ceil

@Composable
fun BMIResultScreen(height: Float, weight: Float) {
    val (BMI, resourceId) = when (ceil(weight / (height * height) * 10000f).toInt()) {
        in 0 until 20 -> "저체중" to R.drawable.baseline_sentiment_dissatisfied_24
        in 20..24 -> "정상" to R.drawable.baseline_sentiment_very_satisfied_24
        in 25..29 -> "과체중" to R.drawable.baseline_sentiment_dissatisfied_24
        else -> "비만" to R.drawable.baseline_sentiment_very_dissatisfied_24
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = BMI, fontSize = 32.sp)
        Image(
            painterResource(id = resourceId),
            modifier = Modifier
                .padding(vertical = 48.dp)
                .width(96.dp)
                .height(96.dp),
            contentDescription = null
        )
    }
}