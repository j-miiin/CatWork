package com.example.catwork.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.ViewholderItemTodoBinding
import com.example.catwork.domain.model.ToDoItem

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ToDoItemViewHolder>() {

    private var toDoList: List<ToDoItem> = listOf()
    private lateinit var toDoItemClickListener: (ToDoItem) -> Unit
    private lateinit var toDoItemCheckListener: (ToDoItem) -> Unit

    inner class ToDoItemViewHolder(
        private val binding: ViewholderItemTodoBinding,
        val toDoItemClickListener: (ToDoItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ToDoItem) = with(binding) {
            checkBox.text = data.content
            checkToDoComplete(data.isChecked)
        }

        fun bindViews(data: ToDoItem) {
            binding.checkBox.setOnClickListener {
                toDoItemCheckListener(
                    data.copy(isChecked = binding.checkBox.isChecked)
                )
                checkToDoComplete(binding.checkBox.isChecked)
            }
            binding.root.setOnClickListener {
                toDoItemClickListener(data)
            }
        }

        private fun checkToDoComplete(isChecked: Boolean) = with(binding) {
            checkBox.isChecked = isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ToDoItemViewHolder {
        val view = ViewholderItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoItemViewHolder(view, toDoItemClickListener)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ToDoItemViewHolder, position: Int) {
        holder.bindData(toDoList[position])
        holder.bindViews(toDoList[position])
    }

    override fun getItemCount(): Int = toDoList.size

    fun setToDoList(
        toDoList: List<ToDoItem>,
        toDoItemClickListener: (ToDoItem) -> Unit,
        toDoItemCheckListener: (ToDoItem) -> Unit
    ) {
        this.toDoList = toDoList
        this.toDoItemClickListener = toDoItemClickListener
        this.toDoItemCheckListener = toDoItemCheckListener
        notifyDataSetChanged()
    }
}