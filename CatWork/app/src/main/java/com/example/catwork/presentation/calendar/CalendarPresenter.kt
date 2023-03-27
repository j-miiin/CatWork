package com.example.catwork.presentation.calendar

import com.example.catwork.domain.usecase.GetDayRecordUseCase
import com.example.catwork.ext.getSelectedDateString
import com.example.catwork.ext.getTodayDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class CalendarPresenter(
    private val view: CalendarContract.View,
    private val getDayRecordUseCase: GetDayRecordUseCase
): CalendarContract.Presenter {

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        val date = getTodayDate()
        fetchCalendarRecord(date[0], date[1] + 1)
    }

    override fun onDestroyView() {}

    override fun fetchCalendarRecord(year: Int, month: Int) {
        scope.launch {
            val calendar = Calendar.getInstance()
            calendar.set(year, month-1, 1)
            val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            var dayRecordList = ArrayList<Int>()
            for (i in 1..lastDay) {
                var dayRecordEntity = getDayRecordUseCase(getSelectedDateString(year, month, i))
                if (dayRecordEntity == null) dayRecordList.add(0)
                else dayRecordList.add(dayRecordEntity.feeling)
            }

            view.showCalendarRecord(dayRecordList)
        }
    }
}