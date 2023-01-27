package com.example.catwork.domain.usecase

import com.example.catwork.data.repository.ToDoItemRepository
import com.example.catwork.domain.model.ToDoItem

class GetToDoListUseCase(private val toDoItemRepository: ToDoItemRepository) {

    suspend operator fun invoke(): List<ToDoItem> = toDoItemRepository.getToDoList()
}