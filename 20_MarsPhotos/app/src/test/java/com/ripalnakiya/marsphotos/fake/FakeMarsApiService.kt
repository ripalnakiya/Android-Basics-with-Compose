package com.ripalnakiya.marsphotos.fake

import com.ripalnakiya.marsphotos.network.MarsApiService
import com.ripalnakiya.marsphotos.network.MarsPhoto

class FakeMarsApiService : MarsApiService {

    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}