package com.example.nomadx.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.nomadx.utils.LocaleHelper

open class BaseActivity : AppCompatActivity() {
    // Этот метод вызывается перед созданием Activity и подменяет контекст на локализованный
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}