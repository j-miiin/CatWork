package com.example.catwork.presentation.home.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogAddTodoBinding
import com.example.catwork.ext.getRandomID
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddToDoDialog(
    context: Context,
    private val okCallBack: (ToDoEntity) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(true)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        addToDoButton.setOnClickListener {
            if (titleEditText.text.isNullOrBlank()) {
                Toast.makeText(context, "할 일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                okCallBack(
                    ToDoEntity(
                        id = getRandomID(),
                        title = titleEditText.text.toString(),
                        content = contentEditText.text.toString(),
                        isChecked = false,
                        dueTo = getTimePickerValue()  // TimePicker에서 설정한 시간
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
        var time = ""
        binding.alarmTimePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            time = "${hour}:${minute}"
        }
        return time
    }
}