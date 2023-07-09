package com.example.stopwatch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var timerJob: Job = Job()

    private val _time: MutableState<Int> = mutableStateOf(0)
    val time: State<Int> get() = _time

    private val _isStart: MutableState<Boolean> = mutableStateOf(false)
    val isStart: State<Boolean> get() = _isStart

    private val _lapTimes = mutableStateListOf<String>()
    val lapTimes get() = _lapTimes

    fun startTimer() {
        if (_isStart.value) {
            _isStart.value = false

            timerJob.cancel()
        } else {
            _isStart.value = true

            timerJob = viewModelScope.launch {
                while (true) {
                    _time.value++
                    delay(10)
                }
            }
        }
    }

    fun resetTimer() {
        timerJob.cancel()
        _time.value = 0
        _isStart.value = false
    }

    fun addLapTime() {
        _lapTimes.add("${_time.value / 100}.${_time.value % 100}")
    }
}