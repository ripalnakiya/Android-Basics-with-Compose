package com.ripalnakiya.inventory

import android.app.Application
import com.ripalnakiya.inventory.data.AppContainer
import com.ripalnakiya.inventory.data.DefaultAppContainer

class InventoryApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}