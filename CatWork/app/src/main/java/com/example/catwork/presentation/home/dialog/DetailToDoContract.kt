package com.example.catwork.presentation.home.dialog

import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.presentation.BasePresenter
import com.example.catwork.presentation.BaseView

interface DetailToDoContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showDetailToDo(toDoEntity: ToDoEntity)
    }

    interface Presenter : BasePresenter {

        fun updateToDoItem(toDoEntity: ToDoEntity)

    }
}