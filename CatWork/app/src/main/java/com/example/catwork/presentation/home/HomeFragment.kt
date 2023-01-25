package com.example.catwork.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catwork.databinding.FragmentHomeBinding
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
        binding?.errorDescriptionTextView?.toVisible()
    }

    override fun showToDoList() {

    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            adapter = HomeAdapter()
        }
    }
}