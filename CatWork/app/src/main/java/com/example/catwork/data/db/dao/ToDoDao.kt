package com.example.catwork.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.catwork.data.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAll(): List<ToDoEntity>

    @Insert
    suspend fun insert(toDoEntity: ToDoEntity)

    @Update
    suspend fun update(toDoEntity: ToDoEntity)

    @Query("DELETE FROM ToDoEntity WHERE id=:id")
    suspend fun delete(id: String)
}