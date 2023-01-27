package com.example.catwork.data.repository

import com.example.catwork.domain.model.ToDoItem

interface ToDoItemRepository {

    suspend fun getToDoList(): List<ToDoItem>

    suspend fun addToDoItem(toDoItem: ToDoItem)

    suspend fun updateToDoItem(toDoItem: ToDoItem)

    suspend fun removeToDoItem(toDoItem: ToDoItem)
}