package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.example.composebasic.utils.customShadow

class ButtonActivity : ComponentActivity() {
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressStatusTitle: String = if (isPressed) "버튼을 누르고 있다." else "버튼을 떼고 있다."

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {}
        ) {
            Text(text = "버튼 1")
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "버튼 1")
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "버튼 1")
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            modifier = Modifier.height(56.dp).width(80.dp)
        ) {
            Text(text = "버튼 1", color = Color.White)
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffDB005B)),
            interactionSource = interactionSource,
            modifier = Modifier.shadow(ambientColor = Color.Blue, spotColor = Color.Blue, elevation = 40.dp, clip = false).height(56.dp).width(80.dp),
        ) {
            Text(text = "버튼 1", color = Color.White)
        }

        Text(text = pressStatusTitle)

//        color: Color = Color.Black,
//        borderRadius: Dp = 0.dp,
//        blurRadius: Dp = 0.dp,
//        offsetY: Dp = 0.dp,
//        offsetX: Dp = 0.dp,
//        spread: Dp = 0f.dp,
//        modifier: Modifier = Modifier
        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffDB005B)),
            modifier = Modifier
                .height(56.dp)
                .width(80.dp)
                .customShadow(color = Color(0xffDB005B), borderRadius = 12.dp, blurRadius = 12.dp)
        ) {
            Text(text = "버튼 1", color = Color.White)
        }
    }
}

@Composable
private fun ButtonContainer(modifier: Modifier = Modifier, text: String) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    ComposeBasicTheme {
        ColumnContainer()
    }
}