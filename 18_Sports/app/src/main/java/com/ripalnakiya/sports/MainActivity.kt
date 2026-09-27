package com.ripalnakiya.sports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ripalnakiya.sports.ui.SportsApp
import com.ripalnakiya.sports.ui.theme.SportsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportsTheme {
                SportsApp()
            }
        }
    }
}