package com.example.catwork.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DayRecordEntity(
    @PrimaryKey val id: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val feeling: Int = 0
)
