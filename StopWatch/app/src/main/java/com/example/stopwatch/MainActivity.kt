package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatch.ui.theme.Purple80
import com.example.stopwatch.ui.theme.StopWatchTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = viewModel<MainViewModel>()
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            StopWatchTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Stopwatch") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center,
                            content = { TimeContent(mainViewModel) }
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = listState
                        ) {
                            itemsIndexed(mainViewModel.lapTimes) { index, laptime ->
                                Text(text = "${index + 1} LAP : $laptime")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            content = {
                                ButtonsContent(
                                    mainViewModel = mainViewModel,
                                    listState = listState,
                                    coroutineScope = coroutineScope
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TimeContent(mainViewModel: MainViewModel) {
    Text(
        text = (mainViewModel.time.value / 100).toString(),
        fontSize = 104.sp,
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center
    )

    Text(
        text = (mainViewModel.time.value % 100).toString(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.End
    )
}

@Composable
private fun ButtonsContent(
    mainViewModel: MainViewModel,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {
    IconButton(
        onClick = {
            mainViewModel.resetTimer()
        },
        modifier = Modifier
            .clip(CircleShape)
            .size(56.dp)
            .background(Color.Red),
    ) {
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = null,
            tint = Color.White
        )
    }

    IconButton(
        onClick = {
            mainViewModel.startTimer()
        },
        modifier = Modifier
            .clip(CircleShape)
            .size(56.dp)
            .background(Color.Green),
    ) {
        Icon(
            painter =
            if (mainViewModel.isStart.value) {
                painterResource(id = R.drawable.baseline_pause_24)
            } else {
                painterResource(id = R.drawable.baseline_play_arrow_24)
            },
            contentDescription = null,
            tint = Color.White
        )

    }

    Button(
        onClick = {
            mainViewModel.addLapTime()
            coroutineScope.launch {
                listState.scrollToItem(mainViewModel.lapTimes.size - 1)
            }
        },
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = "랩 타임", fontSize = 16.sp)
    }
}