package com.example.catwork.presentation.home

import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.presentation.BasePresenter
import com.example.catwork.presentation.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showToDoList(toDoList: List<ToDoEntity>)
    }

    interface Presenter : BasePresenter {

        fun fetchToDoList(createdAt: String)

        fun addToDoItem(toDoEntity: ToDoEntity)

        fun updateToDoItem(toDoEntity: ToDoEntity)

        fun deleteToDoItem(id: String)
    }
}