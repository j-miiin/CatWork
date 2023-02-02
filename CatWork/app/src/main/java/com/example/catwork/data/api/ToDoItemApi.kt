package com.example.catwork.data.api

import com.example.catwork.data.entity.ToDoEntity

interface ToDoItemApi {

    suspend fun getToDoList(): List<ToDoEntity>
}