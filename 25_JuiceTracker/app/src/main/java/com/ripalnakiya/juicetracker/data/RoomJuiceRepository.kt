package com.ripalnakiya.juicetracker.data

import com.ripalnakiya.juicetracker.data.database.Juice
import com.ripalnakiya.juicetracker.data.database.JuiceDao
import kotlinx.coroutines.flow.Flow

class RoomJuiceRepository(private val juiceDao: JuiceDao) : JuiceRepository {
    override val juicesStream: Flow<List<Juice>> = juiceDao.getAll()
    override fun getJuiceStream(id: Long): Flow<Juice?> = juiceDao.get(id)
    override suspend fun addJuice(juice: Juice) = juiceDao.insert(juice)
    override suspend fun deleteJuice(juice: Juice) = juiceDao.delete(juice)
    override suspend fun updateJuice(juice: Juice) = juiceDao.update(juice)
}