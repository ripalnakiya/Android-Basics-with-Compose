package com.ripalnakiya.amphibians.data

import com.ripalnakiya.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}
