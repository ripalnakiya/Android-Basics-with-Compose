package com.ripalnakiya.juicetracker

import android.app.Application
import com.ripalnakiya.juicetracker.data.AppContainer
import com.ripalnakiya.juicetracker.data.DefaultAppContainer

class JuiceTrackerApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}