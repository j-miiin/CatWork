package com.example.catwork.presentation.home.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogDetailTodoBinding
import com.example.catwork.ext.toGone
import com.example.catwork.ext.toVisible
import kotlin.math.min

class DetailToDoDialog(
    context: Context,
    private val toDoEntity: ToDoEntity,
    private val okCallBack: (ToDoEntity) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogDetailTodoBinding

    private var editMode = false
    private var alarmMode = false
    private var alarmHour = 0
    private var alarmMinute = 0

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

        if (toDoEntity.dueTo.isNotEmpty()) alarmMode = true

        initTime()

        alarmCheckBox.setOnCheckedChangeListener { _, isChecked ->
            alarmMode = isChecked
            if (alarmMode) alarmTimePicker.toVisible()
            else alarmTimePicker.toGone()
        }

        alarmTimePicker.setOnTimeChangedListener { _, hour, minute ->
            alarmHour = hour
            alarmMinute = minute
        }

        editButton.setOnClickListener {
            editMode = !editMode
            if (editMode) {
                titleEditText.isFocusableInTouchMode = true
                contentEditText.isFocusableInTouchMode = true
                alarmCheckBox.toVisible()
                alarmCheckBox.isChecked = alarmMode
                if (alarmMode) {
                    alarmTimePicker.toVisible()
                    setTimePickerValue(alarmHour, alarmMinute)
                }
            } else {
                hideKeyboard()
                titleEditText.isFocusableInTouchMode = false
                titleEditText.isFocusable = false
                contentEditText.isFocusableInTouchMode = false
                contentEditText.isFocusable = false
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
                        id = toDoEntity.id,
                        title = titleEditText.text.toString(),
                        content = contentEditText.text.toString(),
                        isChecked = toDoEntity.isChecked,
                        dueTo = if (alarmMode) getTimePickerValue() else "",    // TimePicker에서 설정한 시간
                        createdAt = toDoEntity.createdAt
                    )
                )
                dismiss()
            }
        }
    }

    private fun initTime() {
        if (toDoEntity.dueTo.isNotEmpty()) {
            val time = toDoEntity.dueTo.split(":")
            alarmHour = time[0].toInt()
            alarmMinute = time[1].toInt()
        }
    }

    private fun setTimePickerValue(hour: Int, minute: Int) {
        binding.alarmTimePicker.hour = hour
        binding.alarmTimePicker.minute = minute
    }

    private fun getTimePickerValue(): String {
        val hour = binding.alarmTimePicker.hour
        val minute = binding.alarmTimePicker.minute
        return "${hour}:${minute}"
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}