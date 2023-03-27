package com.example.catwork.presentation.home.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogAddTodoBinding
import com.example.catwork.ext.getRandomID

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

//        alarmCheckBox.setOnCheckedChangeListener { _, isChecked ->
//            hideKeyboard()
//            alarmMode = isChecked
//            if (isChecked) alarmTimePicker.toVisible()
//            else alarmTimePicker.toGone()
//        }

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
//                        dueTo = if (alarmMode) getTimePickerValue() else "",    // TimePicker에서 설정한 시간
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

//    private fun getTimePickerValue(): String {
//        val hour = binding.alarmTimePicker.hour
//        val minute = binding.alarmTimePicker.minute
//        return "${hour}:${minute}"
//    }
}