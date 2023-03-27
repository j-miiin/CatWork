package com.example.catwork.presentation.calendar

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catwork.R
import com.example.catwork.data.entity.DayRecordEntity
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.ViewholderCalendarCellBinding

class CalendarAdapter() : RecyclerView.Adapter<CalendarAdapter.CalendarCellViewHolder>() {

    var dayList = ArrayList<String>()
    var dayRecordList = ArrayList<Int>()
    lateinit var dayCellClickListener: (ToDoEntity) -> Unit

    inner class CalendarCellViewHolder(
        private val binding: ViewholderCalendarCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: String, position: Int) = with(binding) {
            dayTextView.text = data
            if (position == 0 || position % 7 == 0) dayTextView.setTextColor(Color.parseColor("#E97777"))
            if (data != "") {
                val idx = data.toInt()
                setFeelingState(dayRecordList[idx-1])
            }
        }

        private fun setFeelingState(feeling: Int) {
            when (feeling) {
                1 -> binding.recordButton.setImageResource(R.drawable.ic_perfect)
                2 -> binding.recordButton.setImageResource(R.drawable.ic_good)
                3 -> binding.recordButton.setImageResource(R.drawable.ic_soso)
                4 -> binding.recordButton.setImageResource(R.drawable.ic_bad)
                5 -> binding.recordButton.setImageResource(R.drawable.ic_sad)
                6 -> binding.recordButton.setImageResource(R.drawable.ic_tired)
                7 -> binding.recordButton.setImageResource(R.drawable.ic_sick)
                0 -> binding.recordButton.setImageResource(R.drawable.ic_none)
            }
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
        dayRecordList: ArrayList<Int>,
        dayCellClickListener: (ToDoEntity) -> Unit,
    ) {
        this.dayList = dayList
        this.dayRecordList = dayRecordList
        this.dayCellClickListener = dayCellClickListener
        notifyDataSetChanged()
    }
}