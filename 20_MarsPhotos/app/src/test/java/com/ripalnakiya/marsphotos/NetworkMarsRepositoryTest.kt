package com.ripalnakiya.marsphotos

import com.ripalnakiya.marsphotos.data.NetworkMarsPhotosRepository
import com.ripalnakiya.marsphotos.fake.FakeDataSource
import com.ripalnakiya.marsphotos.fake.FakeMarsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() = runTest {
        val repository = NetworkMarsPhotosRepository(marsApiService = FakeMarsApiService())
        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}