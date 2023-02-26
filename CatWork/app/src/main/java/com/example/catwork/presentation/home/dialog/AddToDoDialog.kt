package com.example.catwork.presentation.home.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogAddTodoBinding
import com.example.catwork.ext.getRandomID
import com.example.catwork.ext.getTodayDateString
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddToDoDialog(
    context: Context,
    private val selectedDate: String,
    private val okCallBack: (ToDoEntity) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogAddTodoBinding

    private var alarmMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(true)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        alarmCheckBox.setOnCheckedChangeListener { _, isChecked ->
            alarmMode = isChecked
            if (isChecked) alarmTimePicker.toVisible()
            else alarmTimePicker.toGone()
        }

        addToDoButton.setOnClickListener {
            if (titleEditText.text.isNullOrBlank()) {
                Toast.makeText(context, "할 일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("create Date", selectedDate)
                okCallBack(
                    ToDoEntity(
                        id = getRandomID(),
                        title = titleEditText.text.toString(),
                        content = contentEditText.text.toString(),
                        isChecked = false,
                        dueTo = if (alarmMode) getTimePickerValue() else "",    // TimePicker에서 설정한 시간
                        createdAt = selectedDate
                    )
                )
                dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun getTimePickerValue(): String {
        val hour = binding.alarmTimePicker.hour
        val minute = binding.alarmTimePicker.minute
        return "${hour}:${minute}"
    }
}