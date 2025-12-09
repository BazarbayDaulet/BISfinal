package com.example.nomadx.ui // Убедись, что пакет правильный (nomadx)

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nomadx.R
import com.example.nomadx.viewmodel.MainViewModel

// Важно: R.layout.fragment_add_post должен существовать
class AddPostFragment : Fragment(R.layout.fragment_add_post) {

    private val vm: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Ищем View по ID (они должны совпадать с XML!)
        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etPrice = view.findViewById<EditText>(R.id.etPrice)
        val etDesc = view.findViewById<EditText>(R.id.etDesc)
        val btnSave = view.findViewById<Button>(R.id.btnSave)

        // 2. Если btnSave не найден, приложение упадет здесь.
        // Если мы всё сделали правильно выше, то всё будет ок.
        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val priceString = etPrice.text.toString()
            val desc = etDesc.text.toString()

            if (title.isNotEmpty() && priceString.isNotEmpty()) {
                val price = priceString.toDoubleOrNull() ?: 0.0

                // Сохраняем через ViewModel
                vm.addPost(title, desc, price)

                Toast.makeText(context, "Опубликовано!", Toast.LENGTH_SHORT).show()

                // Очищаем поля
                etTitle.text.clear()
                etPrice.text.clear()
                etDesc.text.clear()
            } else {
                Toast.makeText(context, "Введите название и цену", Toast.LENGTH_SHORT).show()
            }
        }
    }
}