package com.example.catwork.presentation.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.catwork.databinding.FragmentCalendarBinding
import com.example.catwork.ext.getTodayDate
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import java.time.LocalDate

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

        }

        nextMonthDayButton.setOnClickListener {

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

    }
}