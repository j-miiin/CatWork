package com.example.catwork.data.repository

import com.example.catwork.data.db.dao.ToDoDao
import com.example.catwork.data.entity.ToDoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ToDoItemRepositoryImpl(
    private val toDoDao: ToDoDao,
    private val dispatcher: CoroutineDispatcher
) : ToDoItemRepository {

    override suspend fun getToDoList(): List<ToDoEntity> = withContext(dispatcher) {
        toDoDao.getAll()
    }

    override suspend fun addToDoItem(toDoEntity: ToDoEntity) = withContext(dispatcher) {
        toDoDao.insert(toDoEntity)
    }
//
//    override suspend fun updateToDoItem(toDoItem: ToDoEntity) {
//    }
//
//    override suspend fun removeToDoItem(toDoItem: ToDoEntity) {
//    }

}