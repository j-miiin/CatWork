package com.example.catwork.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.databinding.ViewholderItemTodoBinding
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ToDoItemViewHolder>() {

    var toDoList = arrayListOf<ToDoEntity>()
    lateinit var toDoItemClickListener: (ToDoEntity) -> Unit
    lateinit var toDoItemCheckListener: (ToDoEntity) -> Unit
    lateinit var toDoItemDeleteListener: (ToDoEntity) -> Unit

    private var editMode = false

    inner class ToDoItemViewHolder(
        private val binding: ViewholderItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ToDoEntity) = with(binding) {
            toDoText.text = data.title
            checkToDoComplete(data.isChecked)
        }

        fun bindViews(data: ToDoEntity) = with(binding) {
            root.setOnClickListener {
                toDoItemClickListener(data)
                notifyDataSetChanged()
            }

            checkBox.setOnClickListener {
                toDoItemCheckListener(
                    data.copy(isChecked = binding.checkBox.isChecked)
                )
                checkToDoComplete(binding.checkBox.isChecked)
            }

            deleteToDoItemButton.setOnClickListener {
                toDoItemDeleteListener(data)
                toDoList.remove(data)
                notifyDataSetChanged()
            }

            if (editMode) deleteToDoItemButton.toVisible()
            else deleteToDoItemButton.toGone()
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
        toDoList: ArrayList<ToDoEntity>,
        toDoItemClickListener: (ToDoEntity) -> Unit,
        toDoItemCheckListener: (ToDoEntity) -> Unit,
        toDoItemDeleteListener: (ToDoEntity) -> Unit
    ) {
        this.toDoList = toDoList
        this.toDoItemClickListener = toDoItemClickListener
        this.toDoItemCheckListener = toDoItemCheckListener
        this.toDoItemDeleteListener = toDoItemDeleteListener
        notifyDataSetChanged()
    }

    fun setEditMode(mode: Boolean) {
        editMode = mode
        notifyDataSetChanged()
    }
}