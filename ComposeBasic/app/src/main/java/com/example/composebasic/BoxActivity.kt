package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class BoxActivity : ComponentActivity() {
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DummyBox(250, Color(0xff73A9AD))
        DummyBox(200, Color(0xffB3C890))
        DummyBox(150, Color(0xffDBDFAA))
        DummyBox(100, Color(0xffF5F0BB))
    }
}

@Composable
private fun DummyBox(size: Int, color: Color) {
    Box(modifier = Modifier
        .background(color)
        .size(size.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    ComposeBasicTheme {
        BoxContainer()
    }
}