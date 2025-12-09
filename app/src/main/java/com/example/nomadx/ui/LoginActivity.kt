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

class LoginActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (vm.isLogged()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPass = findViewById<EditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            vm.login(etEmail.text.toString(), etPass.text.toString())
        }

        findViewById<TextView>(R.id.tvToRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        vm.loginResult.observe(this) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        vm.errorMsg.observe(this) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}