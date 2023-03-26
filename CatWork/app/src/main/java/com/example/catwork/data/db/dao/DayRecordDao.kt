package com.example.catwork.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.catwork.data.entity.DayRecordEntity

@Dao
interface DayRecordDao {

    @Query("SELECT * FROM DayRecordEntity WHERE year=:year AND month=:month")
    suspend fun getAll(year: Int, month: Int): List<DayRecordEntity>

    @Query("SELECT * FROM DayRecordEntity WHERE id=:id")
    suspend fun get(id: String): DayRecordEntity?

    @Insert
    suspend fun insert(dayRecordEntity: DayRecordEntity)

    @Update
    suspend fun update(dayRecordEntity: DayRecordEntity)
}