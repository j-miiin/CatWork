package com.example.catwork.presentation.mypage

import com.example.catwork.presentation.BasePresenter
import com.example.catwork.presentation.BaseView

interface CalendarContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showCalendarRecord()
    }

    interface Presenter : BasePresenter {

        fun fetchCalendarRecord(date: String)
    }
}