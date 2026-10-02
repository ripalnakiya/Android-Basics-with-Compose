package com.ripalnakiya.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ripalnakiya.amphibians.data.Amphibian

class AmphibiansViewModel : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set
}

sealed interface AmphibianUiState {
    data class Success(val list: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}