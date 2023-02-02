package com.example.catwork.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.ViewholderItemTodoBinding
import com.example.catwork.data.entity.ToDoEntity

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ToDoItemViewHolder>() {

    var toDoList: List<ToDoEntity> = listOf()
    lateinit var toDoItemClickListener: (ToDoEntity) -> Unit
    lateinit var toDoItemCheckListener: (ToDoEntity) -> Unit

    inner class ToDoItemViewHolder(
        private val binding: ViewholderItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ToDoEntity) = with(binding) {
            checkBox.text = data.title
            checkBox.isChecked = data.isChecked
        }

        fun bindViews(data: ToDoEntity) {
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
        return ToDoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ToDoItemViewHolder, position: Int) {
        holder.bindData(toDoList[position])
        holder.bindViews(toDoList[position])
    }

    override fun getItemCount(): Int = toDoList.size

    fun setToDoList(
        toDoList: List<ToDoEntity>,
        toDoItemClickListener: (ToDoEntity) -> Unit,
        toDoItemCheckListener: (ToDoEntity) -> Unit
    ) {
        this.toDoList = toDoList
        this.toDoItemClickListener = toDoItemClickListener
        this.toDoItemCheckListener = toDoItemCheckListener
        notifyDataSetChanged()
    }
}