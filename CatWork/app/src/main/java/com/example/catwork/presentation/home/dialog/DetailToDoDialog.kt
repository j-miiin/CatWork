package com.example.catwork.presentation.home.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogDetailTodoBinding
import com.example.catwork.ext.getRandomID
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import java.util.*

class DetailToDoDialog(
    context: Context,
    private val toDoEntity: ToDoEntity,
    private val okCallBack: (ToDoEntity) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogDetailTodoBinding

    private var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogDetailTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        setCancelable(true)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        titleEditText.setText(toDoEntity.title)
        contentEditText.setText(toDoEntity.content)

        editButton.setOnClickListener {
            editMode = !editMode
            if (editMode) {
                titleEditText.isEnabled = true
                contentEditText.isEnabled = true
                alarmCheckBox.toVisible()
            } else {
                titleEditText.isEnabled = false
                contentEditText.isEnabled = false
                alarmCheckBox.toGone()
                alarmTimePicker.toGone()
            }
        }

        closeButton.setOnClickListener {
            if (titleEditText.text.isNullOrBlank()) {
                Toast.makeText(context, "할 일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (editMode) {
                Toast.makeText(context, "수정을 완료해주세요!", Toast.LENGTH_SHORT).show()
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
    }

    private fun getTimePickerValue(): String {
        var time = ""
        binding.alarmTimePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            time = "${hour}:${minute}"
        }
        return time
    }
}