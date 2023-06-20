package com.example.ktor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ktor.ui.theme.KtorTheme
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch

/** 테스트 중입니다. Ktor 한번 돌려보고 싶었어요 ,, **/
class MainActivity : ComponentActivity() {
    private val client = HttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KtorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")

                    val scope = rememberCoroutineScope()
                    LaunchedEffect(true) {
                        scope.launch {
                            val text = try {
                                greeting()
                            } catch (e: Exception) {
                                e.localizedMessage ?: "error"
                            }

                            Log.i("MainActivity", "[TEST] text: $text")
                        }
                    }
                }
            }
        }
    }

    private suspend fun greeting(): String {
        val response = client.get("https://api.thedogapi.com/v1/breeds?limit=10&page=0")
        return response.bodyAsText()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtorTheme {
        Greeting("Android")
    }
}