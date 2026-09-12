package com.ripalnakiya.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ripalnakiya.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ComposeQuadrant(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        Row (Modifier.weight(1.0F)) {
            Quadrant(
                heading = stringResource(R.string.heading1),
                description = stringResource(R.string.description1),
                color = Color(0xFFEADDFF),
                modifier = Modifier.weight(1.0F),
            )
            Quadrant(
                heading = stringResource(R.string.heading2),
                description = stringResource(R.string.description2),
                color = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1.0F),
            )
        }
        Row(Modifier.weight(1.0F)) {
            Quadrant(
                heading = stringResource(R.string.heading3),
                description = stringResource(R.string.description3),
                color = Color(0xFFB69DF8),
                modifier = Modifier.weight(1.0F),
            )
            Quadrant(
                heading = stringResource(R.string.heading4),
                description = stringResource(R.string.description4),
                color = Color(0xFFF6EDFF),
                modifier = Modifier.weight(1.0F),
            )
        }
    }
}

@Composable
fun Quadrant(heading: String, description: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = heading,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComposeQuadrantPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrant()
    }
}