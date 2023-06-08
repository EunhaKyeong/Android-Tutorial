package com.example.composebasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.example.composebasic.ui.theme.SteelBlue

class TutorialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Container()
                }
            }
        }
    }
}

@Composable
private fun Container() {
    val context: Context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TutorialActivity") },
                backgroundColor = Color.LightGray
            )
        }
    ) {it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = it.calculateTopPadding() + 8.dp, horizontal = it.calculateStartPadding(LayoutDirection.Ltr) + 16.dp),

            ) {
            TutorialBtn(text = "SecondActivity", onClick = {
                context.startActivity(Intent(context, SecondActivity::class.java))
            })

            TutorialBtn(text = "ThirdActivity", onClick = {
                context.startActivity(Intent(context, ThirdActivity::class.java))
            })

            TutorialBtn(text = "FourthActivity", onClick = {
                context.startActivity(Intent(context, FourthActivity::class.java))
            })

            TutorialBtn(text = "BoxActivity", onClick = {
                context.startActivity(Intent(context, BoxActivity::class.java))
            })

            TutorialBtn(text = "BoxWithConstraintsActivity", onClick = {
                context.startActivity(Intent(context, BoxWithConstraintsActivity::class.java))
            })

            TutorialBtn(text = "TextActivity", onClick = {
                context.startActivity(Intent(context, TextActivity::class.java))
            })

            TutorialBtn(text = "ShapeActivity", onClick = {
                context.startActivity(Intent(context, ShapeActivity::class.java))
            })
        }
    }


}

@Composable
fun TutorialBtn(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = SteelBlue)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ComposeBasicTheme {
        Container()
    }
}