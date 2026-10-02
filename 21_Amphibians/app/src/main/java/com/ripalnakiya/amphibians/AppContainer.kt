package com.ripalnakiya.amphibians

import com.ripalnakiya.amphibians.data.AmphibiansRepository
import com.ripalnakiya.amphibians.data.NetworkAmphibiansRepository
import com.ripalnakiya.amphibians.network.AmphibiansApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val amphibiansRepository: AmphibiansRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val amphibiansApiService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }

    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(amphibiansApiService)
    }
}