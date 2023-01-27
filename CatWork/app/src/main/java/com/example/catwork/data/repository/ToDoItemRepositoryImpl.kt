package com.example.catwork.data.repository

import com.example.catwork.data.api.ToDoItemApi
import com.example.catwork.domain.model.ToDoItem
import kotlinx.coroutines.CoroutineDispatcher

class ToDoItemRepositoryImpl(
    private val toDoItemApi: ToDoItemApi,
    private val dispatcher: CoroutineDispatcher
) : ToDoItemRepository {

    override suspend fun getToDoList(): List<ToDoItem> =
        toDoItemApi.getToDoList()


    override suspend fun addToDoItem(toDoItem: ToDoItem) {
    }

    override suspend fun updateToDoItem(toDoItem: ToDoItem) {
    }

    override suspend fun removeToDoItem(toDoItem: ToDoItem) {
    }

}