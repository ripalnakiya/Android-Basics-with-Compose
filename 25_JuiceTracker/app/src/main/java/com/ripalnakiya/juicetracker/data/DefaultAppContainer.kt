package com.ripalnakiya.juicetracker.data

import android.content.Context
import com.ripalnakiya.juicetracker.data.database.AppDatabase

/**
 * [AppContainer] implementation that provides instance of [RoomJuiceRepository]
 */
class DefaultAppContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [JuiceRepository]
     */
    override val trackerRepository: JuiceRepository by lazy {
        RoomJuiceRepository(AppDatabase.getDatabase(context).juiceDao())
    }
}