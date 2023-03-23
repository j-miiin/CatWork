package com.example.catwork.data.repository

import com.example.catwork.data.db.dao.DayRecordDao
import com.example.catwork.data.entity.DayRecordEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DayRecordRepositoryImpl(
    private val dayRecordDao: DayRecordDao,
    private val dispatcher: CoroutineDispatcher
): DayRecordRepository {

    override suspend fun getDayRecordList(year: Int, month: Int): List<DayRecordEntity> = withContext(dispatcher) {
        dayRecordDao.getAll(year, month)
    }

    override suspend fun getDayRecord(id: String): DayRecordEntity = withContext(dispatcher) {
        dayRecordDao.get(id)
    }

    override suspend fun addDayRecord(dayRecordEntity: DayRecordEntity) = withContext(dispatcher) {
        dayRecordDao.insert(dayRecordEntity)
    }
}