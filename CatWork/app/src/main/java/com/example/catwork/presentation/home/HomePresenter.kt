package com.example.catwork.presentation.home

import com.example.catwork.domain.usecase.GetToDoListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class HomePresenter(
    private val view: HomeContract.View,
    private val getToDoListUseCase: GetToDoListUseCase
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()
}