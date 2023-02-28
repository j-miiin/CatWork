package com.example.catwork.presentation.home

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.R
import com.example.catwork.databinding.ViewholderItemTodoBinding
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogDetailTodoBinding
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import com.example.catwork.presentation.home.dialog.DetailToDoDialog

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ToDoItemViewHolder>() {

    lateinit var context: Context
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
            checkBox.isChecked = data.isChecked
        }

        fun bindViews(data: ToDoEntity) = with(binding) {
            root.setOnClickListener {
                val toDoEntity = showDetailToDoDialog(data, adapterPosition)
                toDoItemClickListener(toDoEntity)
            }

            checkBox.setOnClickListener {
                val updateToDoEntity = data.copy(isChecked = checkBox.isChecked)
                toDoList.set(adapterPosition, updateToDoEntity)
                notifyItemChanged(adapterPosition)
                toDoItemCheckListener(updateToDoEntity)
            }

            deleteToDoItemButton.setOnClickListener {
                toDoItemDeleteListener(data)
                toDoList.remove(data)
                notifyDataSetChanged()
            }

            if (editMode) deleteToDoItemButton.toVisible()
            else deleteToDoItemButton.toGone()
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
        context: Context,
        toDoList: ArrayList<ToDoEntity>,
        toDoItemClickListener: (ToDoEntity) -> Unit,
        toDoItemCheckListener: (ToDoEntity) -> Unit,
        toDoItemDeleteListener: (ToDoEntity) -> Unit
    ) {
        this.context = context
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

    private fun showDetailToDoDialog(data: ToDoEntity, position: Int): ToDoEntity {
        var updateToDoEntity = data.copy()
        DetailToDoDialog(context, data) {
            updateToDoEntity = it
            toDoList.set(position, it)
            notifyItemChanged(position)
        }.show()
        return updateToDoEntity
    }
}