package com.example.catwork.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.FragmentHomeBinding
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment


class HomeFragment : ScopeFragment(), HomeContract.View {

    private var binding: FragmentHomeBinding? = null

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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()
    }

    override fun showErrorDescription(message: String) {
        binding?.recyclerView?.toGone()
        binding?.errorDescriptionTextView?.toVisible()
        binding?.errorDescriptionTextView?.text = message
    }

    override fun showToDoList(toDoList: List<ToDoEntity>) {
        binding?.recyclerView?.toVisible()
        binding?.errorDescriptionTextView?.toGone()
        (binding?.recyclerView?.adapter as? HomeAdapter)?.run {
            setToDoList(toDoList,
                toDoItemClickListener = {
                    //TODO Update 화면으로 이동 -> 기능 좀 더 고민
                },
                toDoItemCheckListener = {
                    // TODO Check 값 Update
                })
            notifyDataSetChanged()
        }
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = HomeAdapter()
        }
    }
}