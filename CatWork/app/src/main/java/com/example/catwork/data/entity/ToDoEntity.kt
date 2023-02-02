package com.example.catwork.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ToDoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val isChecked: Boolean = false,
    val dueTo: Date
)
