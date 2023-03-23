package com.example.catwork.data.repository

import com.example.catwork.data.entity.DayRecordEntity

interface DayRecordRepository {

    suspend fun getDayRecordList(year: Int, month: Int): List<DayRecordEntity>

    suspend fun addDayRecord(dayRecordEntity: DayRecordEntity)
}