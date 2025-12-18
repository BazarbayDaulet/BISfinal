package com.example.nomadx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nomadx.R
import com.example.nomadx.utils.LocaleHelper
import com.example.nomadx.viewmodel.MainViewModel

class RegisterActivity : BaseActivity() { // Наследуем от BaseActivity!
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // --- ЛОГИКА СМЕНЫ ЯЗЫКА ---
        findViewById<Button>(R.id.btnRu).setOnClickListener {
            setAppLocale("ru")
        }
        findViewById<Button>(R.id.btnEn).setOnClickListener {
            setAppLocale("en")
        }
        // ---------------------------

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            vm.register(etEmail.text.toString(), etPass.text.toString())
        }

        findViewById<TextView>(R.id.tvToRegister).setOnClickListener {
            finish() // Возвращаемся на экран входа
        }

        vm.registerResult.observe(this) {
            Toast.makeText(this, getString(R.string.success_register), Toast.LENGTH_LONG).show()
            finish()
        }

        vm.errorMsg.observe(this) {
            Toast.makeText(this, getString(R.string.error_user_exists), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAppLocale(code: String) {
        LocaleHelper.setLocale(this, code)
        recreate()
    }
}