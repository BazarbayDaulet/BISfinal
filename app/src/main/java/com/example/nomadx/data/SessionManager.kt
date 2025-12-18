package com.example.nomadx.data

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("oqujoly_session", Context.MODE_PRIVATE)

    fun saveSession(email: String) {
        prefs.edit().putBoolean("is_logged", true).putString("email", email).apply()
    }

    fun logout() {
        // При выходе язык не сбрасываем, удаляем только данные входа
        prefs.edit().remove("is_logged").remove("email").apply()
    }

    fun isLogged(): Boolean = prefs.getBoolean("is_logged", false)
    fun getEmail(): String = prefs.getString("email", "Guest") ?: "Guest"

    // --- НОВЫЕ МЕТОДЫ ДЛЯ ЯЗЫКА ---
    fun saveLanguage(lang: String) {
        prefs.edit().putString("app_lang", lang).apply()
    }

    fun getLanguage(): String {
        return prefs.getString("app_lang", "ru") ?: "ru" // По умолчанию русский
    }
}