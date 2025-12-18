package com.example.nomadx.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nomadx.R
import com.example.nomadx.utils.LocaleHelper
import com.example.nomadx.viewmodel.MainViewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val vm: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.tvEmail).text = vm.getCurrentUserEmail()

        // Кнопка выхода
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            vm.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }

        // Смена языка - Русский
        view.findViewById<Button>(R.id.btnRu).setOnClickListener {
            setLang("ru")
        }

        // Смена языка - Английский
        view.findViewById<Button>(R.id.btnEn).setOnClickListener {
            setLang("en")
        }
    }

    private fun setLang(langCode: String) {
        val context = requireContext()
        // 1. Сохраняем и обновляем конфигурацию
        LocaleHelper.setLocale(context, langCode)

        // 2. Перезапускаем Activity, чтобы ресурсы обновились
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}