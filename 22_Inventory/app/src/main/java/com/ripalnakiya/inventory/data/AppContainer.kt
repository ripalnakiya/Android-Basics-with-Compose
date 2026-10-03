package com.ripalnakiya.inventory.data

import android.content.Context
import com.ripalnakiya.inventory.data.database.InventoryDatabase

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: ItemsRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}