package com.ripalnakiya.racetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ripalnakiya.racetracker.ui.RaceTrackerApp
import com.ripalnakiya.racetracker.ui.theme.RaceTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaceTrackerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RaceTrackerApp()
                }
            }
        }
    }
}