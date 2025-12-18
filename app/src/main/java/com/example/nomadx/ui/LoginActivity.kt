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

class LoginActivity : BaseActivity() {
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (vm.isLogged()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)


        findViewById<Button>(R.id.btnRu).setOnClickListener {
            setAppLocale("ru")
        }
        findViewById<Button>(R.id.btnEn).setOnClickListener {
            setAppLocale("en")
        }


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

        vm.errorMsg.observe(this) {

            Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAppLocale(code: String) {
        LocaleHelper.setLocale(this, code)
        recreate()
    }
}