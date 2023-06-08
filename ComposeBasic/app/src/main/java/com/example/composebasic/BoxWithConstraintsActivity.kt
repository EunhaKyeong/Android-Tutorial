package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class BoxWithConstraintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BoxContainer()
                }
            }
        }
    }
}

@Composable
private fun BoxContainer() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (this.minHeight > 400.dp) {
            DummyBox(color = Color(0xff30A2FF))
        } else {
            DummyBox(color = Color(0xffFFF5B8))
        }

        Text(text = "MinHeight: ${this.minHeight}")
    }
}

@Composable
private fun DummyBox(color: Color) {
    Box(
        modifier = Modifier
            .background(color)
            .size(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeBasicTheme {
        BoxContainer()
    }
}