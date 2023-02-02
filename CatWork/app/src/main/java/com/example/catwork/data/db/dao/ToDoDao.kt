package com.example.catwork.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.catwork.data.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAll(): List<ToDoEntity>
}