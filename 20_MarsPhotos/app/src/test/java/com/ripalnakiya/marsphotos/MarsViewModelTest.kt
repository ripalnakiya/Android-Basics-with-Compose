package com.ripalnakiya.marsphotos

import com.ripalnakiya.marsphotos.fake.FakeDataSource
import com.ripalnakiya.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.ripalnakiya.marsphotos.rules.TestDispatcherRule
import com.ripalnakiya.marsphotos.ui.MarsUiState
import com.ripalnakiya.marsphotos.ui.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(FakeNetworkMarsPhotosRepository())

        assertEquals(
            MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
            marsViewModel.marsUiState
        )
    }
}