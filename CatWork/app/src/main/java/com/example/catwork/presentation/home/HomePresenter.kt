package com.example.catwork.presentation.home

import android.util.Log
import com.example.catwork.R
import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.domain.usecase.*
import com.example.catwork.ext.getTodayDate
import com.example.catwork.ext.getTodayDateString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class HomePresenter(
    private val view: HomeContract.View,
    private val getToDoListUseCase: GetToDoListUseCase,
    private val addToDoUseCase: AddToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
    private val getDayRecordUseCase: GetDayRecordUseCase,
    private val addDayRecordUseCase: AddDayRecordUseCase,
    private val updateDayRecordUseCase: UpdateDayRecordUseCase
) : HomeContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchToDoList(getTodayDateString())
        fetchDayRecord(getTodayDateString())
    }

    override fun onDestroyView() { }

    override fun fetchToDoList(createdAt: String) {
        scope.launch {
            try {
                view.showLoadingIndicator()
                val toDoList = getToDoListUseCase(createdAt)
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

    override fun addToDoItem(toDoEntity: ToDoEntity) {
        scope.launch {
            addToDoUseCase(toDoEntity)
            fetchToDoList(toDoEntity.createdAt)
        }
    }

    override fun updateToDoItem(toDoEntity: ToDoEntity) {
        scope.launch {
            updateToDoUseCase(toDoEntity)
        }
    }

    override fun deleteToDoItem(id: String) {
        scope.launch {
            deleteToDoUseCase(id)
        }
    }

    override fun fetchDayRecord(id: String) {
        scope.launch {
            var dayRecordEntity = getDayRecordUseCase(id)
            if (dayRecordEntity == null) {
                val year = (id.substring(0 until 4)).toInt()
                val month = (id.substring(4 until 6)).toInt()
                val day = (id.substring(6)).toInt()
                dayRecordEntity = DayRecordEntity(id, year, month, day, 0)
                addDayRecordUseCase(dayRecordEntity)
            }
            view.showDayRecord(dayRecordEntity)
        }
    }

    override fun addDayRecord(dayRecordEntity: DayRecordEntity) {
        scope.launch {
            addDayRecordUseCase(dayRecordEntity)
        }
    }

    override fun updateDayRecord(dayRecordEntity: DayRecordEntity) {
        scope.launch {
            updateDayRecordUseCase(dayRecordEntity)
        }
    }
}

