package com.example.catwork.data.db.converters

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun LongToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun DateToLong(date: Date?): Long? {
        return date?.time
    }
}