package com.wiryadev.preferencesample

import android.content.Context

private const val PREFS_NAME = "settings"

class SettingPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(THEME_SETTING, isDarkModeActive)
        editor.apply()
    }

    fun getThemeSetting(): Boolean {
        return preferences.getBoolean(THEME_SETTING, false)
    }

    companion object {
        private const val THEME_SETTING = "theme_setting"
    }
}