package com.ripalnakiya.amphibians.network

import com.ripalnakiya.amphibians.data.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}