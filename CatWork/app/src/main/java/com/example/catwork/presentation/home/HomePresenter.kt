package com.example.catwork.presentation.home

import com.example.catwork.R
import com.example.catwork.domain.usecase.GetToDoListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomePresenter(
    private val view: HomeContract.View,
    private val getToDoListUseCase: GetToDoListUseCase
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchToDoList()
    }

    override fun onDestroyView() { }

    private fun fetchToDoList() = scope.launch {
        try {
            view.showLoadingIndicator()
            val toDoList = getToDoListUseCase()
            view.showToDoList(toDoList)
        } catch (e: Exception) {
            e.printStackTrace()
            view.showErrorDescription(R.string.error_description.toString())
        } finally {
            view.hideLoadingIndicator()
        }
    }
}