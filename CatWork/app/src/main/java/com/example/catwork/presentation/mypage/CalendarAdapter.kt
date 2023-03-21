package com.example.catwork.presentation.mypage

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.R
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.ViewholderCalendarCellBinding

class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.CalendarCellViewHolder>() {

    var dayList = ArrayList<String>()
    lateinit var dayCellClickListener: (ToDoEntity) -> Unit

    inner class CalendarCellViewHolder(
        private val binding: ViewholderCalendarCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: String, position: Int) = with(binding) {
            dayTextView.text = data
            if (position == 0 || position % 7 == 0) dayTextView.setTextColor(Color.parseColor("#E97777"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarCellViewHolder {
        val view = ViewholderCalendarCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarCellViewHolder, position: Int) {
        holder.bindData(dayList[position], position)
    }

    override fun getItemCount(): Int = dayList.size

    fun setDayList(
        dayList: ArrayList<String>,
        dayCellClickListener: (ToDoEntity) -> Unit,
    ) {
        this.dayList = dayList
        this.dayCellClickListener = dayCellClickListener
        notifyDataSetChanged()
    }
}