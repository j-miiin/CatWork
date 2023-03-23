package com.example.catwork.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.catwork.data.entity.DayRecordEntity

@Dao
interface DayRecordDao {

    @Query("SELECT * FROM DayRecordEntity WHERE year=:year AND month=:month")
    suspend fun getAll(year: Int, month: Int): List<DayRecordEntity>
}