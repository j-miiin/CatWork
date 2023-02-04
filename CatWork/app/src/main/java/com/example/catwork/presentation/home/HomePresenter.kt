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
        updateToDoUseCase(toDoEntity)
    }
}

//            val toDoList = listOf(
//                ToDoEntity(
//                    id = "1",
//                    title = "고양이랑 놀기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "2",
//                    title = "청소하기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "3",
//                    title = "숨쉬기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "4",
//                    title = "밥먹기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "5",
//                    title = "아무것도 안하기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "6",
//                    title = "유투브 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "7",
//                    title = "트위치 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "8",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "9",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "10",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "11",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "12",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//                ToDoEntity(
//                    id = "13",
//                    title = "넷플릭스 보기",
//                    content = "재밌겠다",
//                    isChecked = false,
//                    dueTo = Date()
//                ),
//            )