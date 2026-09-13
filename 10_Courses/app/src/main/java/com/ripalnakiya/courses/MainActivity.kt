package com.ripalnakiya.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ripalnakiya.courses.data.DataSource
import com.ripalnakiya.courses.model.Topic
import com.ripalnakiya.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CourseApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CourseApp(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic,modifier: Modifier = Modifier) {
    Card(modifier) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = null,
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
                Text(
                    text = stringResource(topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 1,
                )
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lesson),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = topic.lessons.toString(),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseAppPreview() {
    CoursesTheme {
        CourseApp()
    }
}

@Preview
@Composable
fun TopicCardPreview() {
    CoursesTheme {
        TopicCard(Topic(R.string.painting, 172, R.drawable.painting))
    }
}