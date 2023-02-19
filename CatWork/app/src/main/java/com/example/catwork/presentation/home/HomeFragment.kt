package com.example.catwork.presentation.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.FragmentHomeBinding
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import com.example.catwork.presentation.home.dialog.AddToDoDialog
import com.example.catwork.presentation.home.dialog.DetailToDoDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : ScopeFragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding

    private var editMode = false

    override val presenter: HomeContract.Presenter by inject()

    private val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

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

        yearTextView.text = "${year}년"
        dateTextView.text = "${month+1}월 ${day}일"

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = HomeAdapter()
        }

        addToDoItemButton.setOnClickListener {
            AddToDoDialog(requireContext()) {
                presenter.addToDoItem(it)
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
    }

    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            binding.yearTextView.text = "${year}년"
            binding.dateTextView.text = "${month+1}월 ${day}일"
        }
        DatePickerDialog(requireContext(), dateSetListener, year, month, day).show()
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
}