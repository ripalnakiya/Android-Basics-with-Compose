package com.ripalnakiya.marsphotos.fake

import com.ripalnakiya.marsphotos.data.MarsPhotosRepository
import com.ripalnakiya.marsphotos.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {

    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}