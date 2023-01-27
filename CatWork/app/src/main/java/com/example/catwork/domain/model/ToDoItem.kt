package com.example.catwork.domain.model

import java.util.Date

data class ToDoItem(
    val id: String? = null,
    val content: String? = null,
    val isChecked: Boolean = false,
    val dueTo: Date? = null,
    val userId: String? = null
)
