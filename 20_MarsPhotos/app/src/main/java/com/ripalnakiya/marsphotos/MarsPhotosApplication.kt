package com.ripalnakiya.marsphotos

import android.app.Application
import com.ripalnakiya.marsphotos.data.AppContainer
import com.ripalnakiya.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}