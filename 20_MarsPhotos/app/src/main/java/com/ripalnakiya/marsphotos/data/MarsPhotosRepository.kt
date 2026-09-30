package com.ripalnakiya.marsphotos.data

import com.ripalnakiya.marsphotos.network.MarsApiService
import com.ripalnakiya.marsphotos.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}