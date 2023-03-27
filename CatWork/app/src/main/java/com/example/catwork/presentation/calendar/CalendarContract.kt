package com.example.catwork.presentation.calendar

import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.presentation.BasePresenter
import com.example.catwork.presentation.BaseView

interface CalendarContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showCalendarRecord(dayRecordList: ArrayList<Int>)
    }

    interface Presenter : BasePresenter {

        fun fetchCalendarRecord(year:Int, month: Int)
    }
}