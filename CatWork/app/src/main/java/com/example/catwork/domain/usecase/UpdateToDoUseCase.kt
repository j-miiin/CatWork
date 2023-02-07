package com.example.catwork.domain.usecase

import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.data.repository.ToDoItemRepository

class UpdateToDoUseCase(private val toDoItemRepository: ToDoItemRepository) {

    suspend operator fun invoke(toDoEntity: ToDoEntity) = toDoItemRepository.updateToDoItem(toDoEntity)
}