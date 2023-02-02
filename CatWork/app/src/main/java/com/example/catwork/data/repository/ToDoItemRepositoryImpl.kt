package com.example.catwork.data.repository

import com.example.catwork.data.api.ToDoItemApi
import com.example.catwork.data.db.dao.ToDoDao
import com.example.catwork.data.entity.ToDoEntity
import kotlinx.coroutines.CoroutineDispatcher

class ToDoItemRepositoryImpl(
    private val toDoDao: ToDoDao,
    private val dispatcher: CoroutineDispatcher
) : ToDoItemRepository {

    override suspend fun getToDoList(): List<ToDoEntity> =
        toDoDao.getAll()

//    override suspend fun addToDoItem(toDoItem: ToDoEntity) {
//    }
//
//    override suspend fun updateToDoItem(toDoItem: ToDoEntity) {
//    }
//
//    override suspend fun removeToDoItem(toDoItem: ToDoEntity) {
//    }

}