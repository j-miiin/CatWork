package com.example.catwork.data.api

import com.example.catwork.data.entity.ToDoEntity

class ToDoItemFirestoreApi(

) : ToDoItemApi {

    override suspend fun getToDoList(): List<ToDoEntity> {
        return listOf()
    }
}