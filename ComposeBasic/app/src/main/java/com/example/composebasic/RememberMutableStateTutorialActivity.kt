package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme

class RememberMutableStateTutorialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting3()
                }
            }
        }
    }
}

/**
 * Compose 의 State 와 MutableState 를 사용하면 Compose 에서 데이터의 상태를 관찰할 수 있다.
 * mutableStateOf: 이 키워드를 통해 MutableState 데이터를 만들 수 있다.
 * 하지만 관찰 후 상태를 변경한다고 화면이 바뀌지 않는다.
 * 아래 cnt 변수는 상태가 변경되면 (==데이터 업데이트) composition -> recomposition 상태가 된다.
 * 이때 Column 안에 있는 UI 가 모두 다시 만들어지기 때문에 cnt 는 초기화되어 0이 된다.
 * 따라서 이를 방지하기 위해 remember 키워드가 필요한 것이다.
 * remember 키워드는 recomposition 이 되더라도 데이터를 보장하고 싶을 때 사용할 수 있는 키워드이다.
**/
@Composable
fun Greeting3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var cnt by remember { mutableStateOf(0) }

        Text(text = "cnt: $cnt")
        Button(
            onClick = {
                cnt += 1
            }
        ) {
            Text(text = "Add Cnt")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicTheme {
        Greeting3()
    }
}