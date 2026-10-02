package com.ripalnakiya.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ripalnakiya.amphibians.AmphibiansApplication
import com.ripalnakiya.amphibians.data.Amphibian
import com.ripalnakiya.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AmphibiansViewModel(val amphibiansRepository: AmphibiansRepository) : ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        fetchAmphibians()
    }

    fun fetchAmphibians() {
        viewModelScope.launch {
            amphibianUiState = try {
                AmphibianUiState.Success(amphibiansRepository.getAmphibians())
            } catch (e: IOException) {
                AmphibianUiState.Error
            } catch (_: HttpException) {
                AmphibianUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository)
            }
        }
    }
}

sealed interface AmphibianUiState {
    data class Success(val list: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}