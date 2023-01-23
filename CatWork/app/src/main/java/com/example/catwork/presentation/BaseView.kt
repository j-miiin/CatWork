package com.example.catwork.presentation

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}