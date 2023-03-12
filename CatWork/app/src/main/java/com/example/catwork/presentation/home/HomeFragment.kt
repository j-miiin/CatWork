package com.example.catwork.presentation.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.FragmentHomeBinding
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.ext.*
import com.example.catwork.presentation.home.alarm.AlarmFunctions
import com.example.catwork.presentation.home.dialog.AddToDoDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import kotlin.collections.ArrayList


class HomeFragment: ScopeFragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    private var editMode = false

    private val alarmFunctions = AlarmFunctions(requireContext())

    override val presenter: HomeContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews() = with(binding) {

        val dateInfo = getTodayDate()
        selectedYear = dateInfo[0]
        selectedMonth = dateInfo[1] + 1
        selectedDay = dateInfo[2]

        yearTextView.text = "${selectedYear}년"
        dateTextView.text = "${selectedMonth}월 ${selectedDay}일"

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = HomeAdapter()
        }

        addToDoItemButton.setOnClickListener {
            AddToDoDialog(requireContext(), getSelectedDateString(selectedYear, selectedMonth, selectedDay)) {
                presenter.addToDoItem(it)
                setAlarm(it.createdAt, it.dueTo, (1..100000).random(), it.title)
            }.show()
        }

        editButton.setOnClickListener {
            editMode = !editMode
            (binding.recyclerView.adapter as HomeAdapter).run {
                setEditMode(editMode)
            }
        }

        yearTextView.setOnClickListener {
            showDatePickerDialog()
        }

        dateTextView.setOnClickListener {
            showDatePickerDialog()
        }

        yesterdayButton.setOnClickListener {
            val yesterdayDateInfo = getYesterdayDate(selectedYear, selectedMonth-1, selectedDay)
            selectedYear = yesterdayDateInfo[0]
            selectedMonth = yesterdayDateInfo[1] + 1
            selectedDay = yesterdayDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.dateTextView.text = "${selectedMonth}월 ${selectedDay}일"

            presenter.fetchToDoList(getSelectedDateString(selectedYear, selectedMonth, selectedDay))
        }

        tomorrowDayButton.setOnClickListener {
            val tomorrowDateInfo = getTomorrowDate(selectedYear, selectedMonth-1, selectedDay)
            selectedYear = tomorrowDateInfo[0]
            selectedMonth = tomorrowDateInfo[1] + 1
            selectedDay = tomorrowDateInfo[2]

            binding.yearTextView.text = "${selectedYear}년"
            binding.dateTextView.text = "${selectedMonth}월 ${selectedDay}일"

            presenter.fetchToDoList(getSelectedDateString(selectedYear, selectedMonth, selectedDay))
        }
    }

    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            selectedYear = year
            selectedMonth = month + 1
            selectedDay = day

            binding.yearTextView.text = "${selectedYear}년"
            binding.dateTextView.text = "${selectedMonth}월 ${selectedDay}일"

            presenter.fetchToDoList(getSelectedDateString(selectedYear, selectedMonth, selectedDay))
        }

        DatePickerDialog(requireContext(), dateSetListener, selectedYear, selectedMonth-1, selectedDay).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
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

    override fun showToDoList(toDoList: List<ToDoEntity>) {
        binding.recyclerView.toVisible()
        binding.errorDescriptionTextView.toGone()
        (binding.recyclerView.adapter as HomeAdapter).run {
            setToDoList(
                requireContext(),
                toDoList as ArrayList<ToDoEntity>,
                toDoItemClickListener = {
                    presenter.updateToDoItem(it)
                },
                toDoItemCheckListener = {
                    presenter.updateToDoItem(it)
                },
                toDoItemDeleteListener = {
                    presenter.deleteToDoItem(it.id)
                })
        }
    }

    private fun setAlarm(date: String, time: String, alarmCode: Int, content: String) {
        val alarmTime = "$date $time"
        alarmFunctions.callAlarm(alarmTime, alarmCode, content)
    }
}