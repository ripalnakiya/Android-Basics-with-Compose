package com.ripalnakiya.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ripalnakiya.businesscard.ui.theme.BlueExtraDark
import com.ripalnakiya.businesscard.ui.theme.BusinessCardTheme
import com.ripalnakiya.businesscard.ui.theme.GreenDark
import com.ripalnakiya.businesscard.ui.theme.GreenLight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(GreenLight),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInfo()

        Spacer(Modifier.size(240.dp))

        ContactInfo()
    }
}

@Composable
fun UserInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .background(BlueExtraDark)
                .size(120.dp),
        )
        Text(
            text = "Ripal Nakiya",
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
        )
        Text(
            text = "Android Developer",
            color = GreenDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    Column(modifier.padding(bottom = 80.dp)) {
        ContactRow(painterResource(R.drawable.ic_call), "0123456789")
        ContactRow(painterResource(R.drawable.ic_email), "example@email.com")
    }
}

@Composable
fun ContactRow(painter: Painter, contact: String, modifier: Modifier = Modifier) {
    Row(modifier.padding(8.dp)) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = GreenDark,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = contact
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}