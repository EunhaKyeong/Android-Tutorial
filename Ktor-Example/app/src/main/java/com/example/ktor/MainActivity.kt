package com.example.ktor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ktor.ui.theme.KtorTheme
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

/** 테스트 중입니다. Ktor 한번 돌려보고 싶었어요 ,, **/
class MainActivity : ComponentActivity() {
    private val client = HttpClient()

    private val onSendDataSubject = MutableSharedFlow<Int>()
    private val gbPayUseEvent: MutableSharedFlow<GbPayUseEvent> = MutableSharedFlow<GbPayUseEvent>()

    private val testJob: Job = Job().apply {
        lifecycleScope.launch {
            for (i in 1 .. 50) {
                Log.i("MainActivity", "onSendDataSubject emit! -> $i")
                onSendDataSubject.emit(i)
                delay(1000L)
            }
        }
    }

    private fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> {
        var job: Job = Job().apply { complete() }

        return onCompletion { job.cancel() }.run {
            flow {
                coroutineScope {
                    collect { value ->
                        if (!job.isActive) {
                            emit(value)
                            job = launch { delay(windowDuration) }
                        }
                    }
                }
            }
        }
    }

    init {
        lifecycleScope.launch {
            onSendDataSubject
                .throttleFirst(5000L).collect {
                    Log.i("MainActivity", "onSendDataSubject collect! -> $it")
                }

            testJob.join()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KtorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android") {
                        lifecycleScope.launch {
                            gbPayUseEvent.emit(ShowManageActivityEvent)
                            gbPayUseEvent.emit(NfcSettingsPopupEvent)
                            gbPayUseEvent.emit(HceEvent)
                        }
                    }

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

        observe()

        val sharedNumbers = MutableStateFlow<Int>()
        var numbers: Flow<Pair<Int, Int>> = flowOf()
        lifecycleScope.launch {
            sharedNumbers.distinctUntilChanged().map {
                Pair<Int, Int>(it, it)
            }.collect {
                Log.i("MainActivity", "[TEST] sharedNumbers: $it")

                numbers = flow {
                    emit(it)
                }
            }
        }

        val li = listOf<Int>(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)

        for (l in li) {
            lifecycleScope.launch {
                sharedNumbers.emit(l)
            }
        }

        lifecycleScope.launch {
            numbers.distinctUntilChanged().collect {
                Log.i("MainActivity", "[TEST] numbers: $it")
            }
        }

    }

    override fun onResume() {
        super.onResume()


    }

    private suspend fun greeting(): String {
        val response = client.get("https://api.thedogapi.com/v1/breeds?limit=10&page=0")
        return response.bodyAsText()
    }

    private fun observe() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                gbPayUseEvent.collect {gbPayUseEvent ->
                    when (gbPayUseEvent) {
                        is ToastEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> ToastEvent")
                        }
                        is ShowManageActivityEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> ShowManageActivityEvent")
                        }
                        is MoveToPageEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> MoveToPageEvent")
                        }
                        is NfcSettingsPopupEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> NfcSettingsPopupEvent")
                        }
                        is HceEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> HceEvent")
                        }
                        is StartNFCServiceEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> StartNFCServiceEvent")
                        }
                        is FinishEvent -> {
                            Log.i("MainActivity", "gbPayUseEvent collect! -> FinishEvent")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: (Int) -> (Unit)) {
    ClickableText(
        text = AnnotatedString("Hello $name!"),
        modifier = modifier,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KtorTheme {
        Greeting("Android") {

        }
    }
}