package com.ripalnakiya.juicetracker.data

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val trackerRepository: JuiceRepository
}