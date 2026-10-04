package com.ripalnakiya.bluromatic

import android.app.Application
import com.ripalnakiya.bluromatic.data.AppContainer
import com.ripalnakiya.bluromatic.data.DefaultAppContainer

class BluromaticApplication : Application()  {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}