package com.example.catwork.presentation.mypage

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class CalendarPresenter(
    private val view: CalendarContract.View
): CalendarContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchCalendarRecord("")
    }

    override fun onDestroyView() {}

    override fun fetchCalendarRecord(date: String) {
        view.showCalendarRecord()
    }
}