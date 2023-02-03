package com.example.catwork.presentation.home.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.catwork.data.entity.ToDoEntity
import com.example.catwork.databinding.DialogAddTodoBinding
import com.example.catwork.ext.getRandomID
import java.util.Date

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
                        dueTo = Date()  // TimePicker에서 설정한 시간
                    )
                )
                dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}