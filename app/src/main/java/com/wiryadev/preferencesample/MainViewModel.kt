package com.wiryadev.preferencesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(
    private val settingPrefs: SettingPreferences
) : ViewModel() {

    private val _isDarkModeActive: MutableLiveData<Boolean> = MutableLiveData()
    val isDarkModeActive: LiveData<Boolean> get() = _isDarkModeActive

    init {
        checkDarkModeStatus()
    }

    fun checkDarkModeStatus() {
        _isDarkModeActive.value = settingPrefs.getThemeSetting()
    }

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        settingPrefs.saveThemeSetting(isDarkModeActive)
    }

}

class MainViewModelFactory(
    private val settingPrefs: SettingPreferences
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(settingPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}