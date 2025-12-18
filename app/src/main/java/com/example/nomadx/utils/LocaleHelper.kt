package com.example.nomadx.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import com.example.nomadx.data.SessionManager
import java.util.Locale

object LocaleHelper {

    fun onAttach(context: Context): Context {
        val lang = SessionManager(context).getLanguage()
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, languageCode: String): Context {
        SessionManager(context).saveLanguage(languageCode)
        return updateResources(context, languageCode)
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res: Resources = context.resources
        val config: Configuration = Configuration(res.configuration)

        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}