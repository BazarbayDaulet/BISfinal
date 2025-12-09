package com.example.nomadx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nomadx.R
import com.example.nomadx.viewmodel.MainViewModel

class RegisterActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnRegister).setOnClickListener { // ID кнопки btnRegister
            vm.register(etEmail.text.toString(), etPass.text.toString())
        }

        findViewById<TextView>(R.id.tvToRegister).setOnClickListener { // Текст "Уже есть аккаунт"
            finish()
        }

        vm.registerResult.observe(this) {
            Toast.makeText(this, "Успешно! Теперь войдите.", Toast.LENGTH_LONG).show()
            finish()
        }
        vm.errorMsg.observe(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}