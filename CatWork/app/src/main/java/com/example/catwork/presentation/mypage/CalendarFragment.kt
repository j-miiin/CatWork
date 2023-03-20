package com.example.catwork.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catwork.databinding.FragmentCalendarBinding
import com.example.catwork.ext.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import java.util.Calendar

class CalendarFragment : ScopeFragment(), CalendarContract.View {

    private lateinit var binding: FragmentCalendarBinding

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    override val presenter: CalendarContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentCalendarBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun initViews() = with(binding) {

        val dateInfo = getTodayDate()
        selectedYear = dateInfo[0]
        selectedMonth = dateInfo[1] + 1
        selectedDay = dateInfo[2]

        yearTextView.text = "${selectedYear}년"
        monthTextView.text = "${selectedMonth}월"

        lastMonthButton.setOnClickListener {
            val lastMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, true, true)
            selectedYear = lastMonthDateInfo[0]
            selectedMonth = lastMonthDateInfo[1] + 1
            selectedDay = lastMonthDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.monthTextView.text = "${selectedMonth}월"

            presenter.fetchCalendarRecord(getSelectedDateString(selectedYear, selectedMonth, selectedDay))
        }

        nextMonthDayButton.setOnClickListener {
            val nextMonthDateInfo = getLastOrNextMonthOrDay(selectedYear, selectedMonth-1, selectedDay, false, true)
            selectedYear = nextMonthDateInfo[0]
            selectedMonth = nextMonthDateInfo[1] + 1
            selectedDay = nextMonthDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.monthTextView.text = "${selectedMonth}월"

            presenter.fetchCalendarRecord(getSelectedDateString(selectedYear, selectedMonth, selectedDay))
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 7)
            adapter = CalendarAdapter()
        }
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun showErrorDescription(message: String) {
        binding.recyclerView.toGone()
        binding.errorDescriptionTextView.toVisible()
        binding.errorDescriptionTextView.text = message
    }

    override fun showCalendarRecord() {
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()
        (binding.recyclerView.adapter as CalendarAdapter).run {
            setDayList(
                getDayList(),
                dayCellClickListener = {

                }
            )
        }
    }

    private fun getDayList(): ArrayList<String> {
        var dayList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        calendar.set(selectedYear, selectedMonth, selectedDay)
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.set(selectedYear, selectedMonth, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1..41) {
            if (i <= firstDayOfWeek || i > (lastDay + firstDayOfWeek)) dayList.add("")
            else dayList.add((i - firstDayOfWeek).toString())
        }

        return dayList
    }
}