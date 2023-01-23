package com.example.catwork.presentation.home

import com.example.catwork.presentation.BasePresenter
import com.example.catwork.presentation.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showToDoList()
    }

    interface Presenter : BasePresenter
}