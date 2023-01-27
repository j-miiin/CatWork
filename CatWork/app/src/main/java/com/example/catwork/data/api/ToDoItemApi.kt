package com.example.catwork.data.api

import com.example.catwork.domain.model.ToDoItem

interface ToDoItemApi {

    suspend fun getToDoList(): List<ToDoItem>
}