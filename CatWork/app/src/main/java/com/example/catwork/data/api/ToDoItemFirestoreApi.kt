package com.example.catwork.data.api

import com.example.catwork.domain.model.ToDoItem

class ToDoItemFirestoreApi(

) : ToDoItemApi {

    override suspend fun getToDoList(): List<ToDoItem> {
        return listOf()
    }
}