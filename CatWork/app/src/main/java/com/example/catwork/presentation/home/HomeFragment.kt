package com.example.catwork.presentation.home

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


class HomeFragment : ScopeFragment(), HomeContract.View {

    private lateinit var binding: FragmentHomeBinding

    private var editMode = false

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

    private fun initViews(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = HomeAdapter()
        }

        binding.addToDoItemButton.setOnClickListener {
            AddToDoDialog(requireContext()) {
                presenter.addToDoItem(it)
            }.show()
        }

        binding.editButton.setOnClickListener {
            editMode = !editMode
            (binding.recyclerView.adapter as HomeAdapter).run {
                setEditMode(editMode)
            }
        }
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
                toDoList as ArrayList<ToDoEntity>,
                toDoItemClickListener = { toDoEntity ->
                    var updateToDoEntity = toDoEntity.copy()
                    DetailToDoDialog(requireContext(), toDoEntity) {
                        updateToDoEntity = toDoEntity.copy(
                            title = it.title,
                            content = it.content,
                            dueTo = it.dueTo
                        )
                        presenter.updateToDoItem(updateToDoEntity)
                    }.show()
                    presenter.fetchToDoList()
                    updateToDoEntity
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