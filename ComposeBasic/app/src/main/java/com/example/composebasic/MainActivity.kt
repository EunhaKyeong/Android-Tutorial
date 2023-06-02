package com.example.composebasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.example.composebasic.ui.theme.Purple200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView 와 동일
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

//뷰
@Composable
fun Greeting(name: String) {
    val context: Context = LocalContext.current

    /** Scaffold: 머티리얼 구성요소를 일반 화면 패턴으로 결합하는 편리한 레이아웃을 제공 **/
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MainActivity") },
                backgroundColor = Color.Green
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    context.startActivity(Intent(context, TutorialActivity::class.java))
                },
                backgroundColor = Purple200
            ) {
                /* FAB content */
                Icon(Icons.Filled.Add, null)
            }
        }
    ) {
        //Vertical Linear
        Column(
            modifier = Modifier
                .background(Color.Magenta)
                .fillMaxSize()
                .padding(it)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            for (i in 1..30) {
                MyRowItem("TEST$i")
            }
        }
    }
}

@Composable
fun MyRowItem(msg: String) {
    //Horizontal Linear
    Row(
        modifier = Modifier
            .background(Color.Red)
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = msg,
            modifier = Modifier
                .background(Color.Yellow)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = msg,
            modifier = Modifier
                .background(Color.Green)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = msg,
            modifier = Modifier
                .background(Color.Blue)
                .padding(10.dp))
    }
}

//미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greeting("안드로이드")
    }
}