package com.ripalnakiya.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ripalnakiya.amphibians.ui.AmphibiansApp
import com.ripalnakiya.amphibians.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansTheme {
                AmphibiansApp()
            }
        }
    }
}