package com.example.catwork.domain.usecase

import com.example.catwork.data.repository.ToDoItemRepository
import com.example.catwork.data.entity.ToDoEntity

class GetToDoListUseCase(private val toDoItemRepository: ToDoItemRepository) {

    suspend operator fun invoke(createdAt: String): List<ToDoEntity> = toDoItemRepository.getToDoList(createdAt)
}