package com.example.timerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.timerapp.ui.theme.TimerAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TimerAppTheme {
                TimerScreen()
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun TimerScreen() {

    var timeLeft by rememberSaveable { mutableStateOf(300) }
    var isRunning by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000)
            timeLeft--
        }

        if (timeLeft == 0) {
            isRunning = false
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            fontSize = 48.sp
        )

        Button(
            onClick = {
                isRunning = true
            }
        ) {
            Text("START")
        }

        Button(
            onClick = {
                isRunning = false
            }
        ) {
            Text("STOP")
        }

        Button(
            onClick = {
                timeLeft = 300
                isRunning = false
            }
        ) {
            Text("RESET")
        }
    }
}