package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class ShapeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ColumnContainer()
                }
            }
        }
    }
}

@Composable
private fun ColumnContainer() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {
        Box(modifier = Modifier
            .size(80.dp)
            .background(Color(0xff606C5D)))

        Box(modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color(0xffFFF4F4)))

        Box(modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xffF7E6C4)))

        Box(modifier = Modifier
            .size(80.dp)
            .clip(CutCornerShape(10.dp))
            .background(Color(0xffF1C376)))

        Box(modifier = Modifier
            .size(80.dp)
            .drawWithCache {
                val path = Path().apply {
                    moveTo(size.width / 2f, 0f)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                onDrawBehind {
                    drawPath(path, Color(0xff025464))
                }
            })

        Box(modifier = Modifier
            .size(50.dp)
            .drawWithCache {
                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(size.width, size.height / 2f)
                    lineTo(0f, size.height)
                    close()
                }
                onDrawBehind {
                    drawPath(path, Color(0xffE57C23))
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    ComposeBasicTheme {
        ColumnContainer()
    }
}