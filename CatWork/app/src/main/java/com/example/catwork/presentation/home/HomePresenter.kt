package com.example.catwork.presentation.home

import com.example.catwork.R
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.domain.usecase.AddToDoUseCase
import com.example.catwork.domain.usecase.GetToDoListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class HomePresenter(
    private val view: HomeContract.View,
    private val getToDoListUseCase: GetToDoListUseCase,
    private val addToDoUseCase: AddToDoUseCase
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchToDoList()
    }

    override fun onDestroyView() { }

    override fun fetchToDoList() {
        scope.launch {
            try {
                view.showLoadingIndicator()
                val toDoList = getToDoListUseCase()
                if (toDoList.isEmpty()) {
                    view.showErrorDescription("오늘의 할 일을 입력해보세요!")
                } else {
                    view.showToDoList(toDoList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                view.showErrorDescription(R.string.error_description.toString())
            } finally {
                view.hideLoadingIndicator()
            }
        }
    }

    override fun addToDo(toDoEntity: ToDoEntity) {
        scope.launch {
            addToDoUseCase(toDoEntity)
        }
        fetchToDoList()
    }

    override fun updateToDoEntity(toDoEntity: ToDoEntity) {
//        updateToDoUseCase(toDoEntity)
    }
}

//val toDoList = listOf(
//    ToDoEntity(
//        id = "1",
//        title = "Wavve 인턴 지원하기",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//    ToDoEntity(
//        id = "2",
//        title = "청소하기",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//    ToDoEntity(
//        id = "3",
//        title = "고양이랑 놀기",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//    ToDoEntity(
//        id = "4",
//        title = "알고리즘 문제 풀기",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//    ToDoEntity(
//        id = "5",
//        title = "CatWork 프로젝트 진행",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//    ToDoEntity(
//        id = "6",
//        title = "Wavve 보기",
//        content = "재밌겠다",
//        isChecked = false,
//        dueTo = Date()
//    ),
//)

