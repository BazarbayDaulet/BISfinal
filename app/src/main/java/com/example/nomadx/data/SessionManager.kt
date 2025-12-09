package com.example.nomadx.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("oqujoly_session", Context.MODE_PRIVATE)

    fun saveSession(email: String) {
        prefs.edit().putBoolean("is_logged", true).putString("email", email).apply()
    }

    fun logout() {
        prefs.edit().clear().apply()
    }

    fun isLogged(): Boolean = prefs.getBoolean("is_logged", false)
    fun getEmail(): String = prefs.getString("email", "Гость") ?: "Гость"
}