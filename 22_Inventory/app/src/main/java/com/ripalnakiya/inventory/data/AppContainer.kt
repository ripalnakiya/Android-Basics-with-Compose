package com.ripalnakiya.inventory.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository()
    }
}