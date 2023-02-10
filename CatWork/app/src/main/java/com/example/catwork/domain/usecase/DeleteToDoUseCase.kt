package com.example.catwork.domain.usecase

import com.example.catwork.data.repository.ToDoItemRepository

class DeleteToDoUseCase(private val toDoItemRepository: ToDoItemRepository) {

    suspend operator fun invoke(id: String) = toDoItemRepository.deleteToDoItem(id)
}