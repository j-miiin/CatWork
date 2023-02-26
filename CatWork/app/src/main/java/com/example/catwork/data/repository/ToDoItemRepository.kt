package com.example.catwork.data.repository

import com.example.catwork.data.entity.ToDoEntity

interface ToDoItemRepository {

    suspend fun getToDoList(createdAt: String): List<ToDoEntity>

    suspend fun addToDoItem(toDoEntity: ToDoEntity)

    suspend fun updateToDoItem(toDoItem: ToDoEntity)

    suspend fun deleteToDoItem(id: String)
}