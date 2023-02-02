package com.example.catwork.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catwork.data.db.converters.Converters
import com.example.catwork.data.db.dao.ToDoDao
import com.example.catwork.data.entity.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class ToDoDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "ToDoDatabase.db"

        fun build(context: Context) : ToDoDatabase =
            Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()
    }

    abstract fun toDoDao(): ToDoDao
}