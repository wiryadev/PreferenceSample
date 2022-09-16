package com.wiryadev.preferencesample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val PREFS_NAME = "settings"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_NAME)

class SettingPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    companion object {
        private const val THEME_SETTING = "theme_setting"

        private val THEME_KEY = booleanPreferencesKey(THEME_SETTING)
    }
}