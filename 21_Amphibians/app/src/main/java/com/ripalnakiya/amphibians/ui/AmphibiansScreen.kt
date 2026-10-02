package com.ripalnakiya.amphibians.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ripalnakiya.amphibians.R
import com.ripalnakiya.amphibians.data.Amphibian
import com.ripalnakiya.amphibians.ui.theme.AmphibiansTheme

@Composable
fun AmphibiansApp() {
    Scaffold(
        topBar = {
            AmphibiansTopAppBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val viewModel: AmphibiansViewModel = viewModel()

        when(val amphibianUiState = viewModel.amphibianUiState) {
            is AmphibianUiState.Loading -> AmphibianNotAvailable(R.drawable.ic_loading, R.string.loading,)
            is AmphibianUiState.Error -> AmphibianNotAvailable(R.drawable.ic_error, R.string.error)
            is AmphibianUiState.Success -> AmphibiansList(amphibianUiState.list, Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) }
    )
}

@Composable
fun AmphibianNotAvailable(
    iconResource: Int,
    textResource: Int,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(iconResource),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = stringResource(textResource),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun AmphibiansList(
    list: List<Amphibian>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(list) { amphibian ->
            AmphibianItem(amphibian)
        }
    }
}

@Composable
fun AmphibianItem(
    amphibian: Amphibian,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(183, 236, 185, 255))
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.item_heading, amphibian.name, amphibian.type),
                style = MaterialTheme.typography.headlineMedium,
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_loading),
                error = painterResource(R.drawable.ic_error),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibianNotAvailablePreview() {
    AmphibiansTheme {
        AmphibianNotAvailable(R.drawable.ic_loading, R.string.loading)
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibianItemPreview() {
    AmphibiansTheme {
        AmphibianItem(
            Amphibian(
                stringResource(R.string.placeholder_amphibian_name),
                stringResource(R.string.placeholder_amphibian_type),
                stringResource(R.string.placeholder_amphibian_description),
                ""
            )
        )
    }
}
