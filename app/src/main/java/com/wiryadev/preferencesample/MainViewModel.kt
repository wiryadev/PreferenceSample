package com.wiryadev.preferencesample

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val settingPrefs: SettingPreferences
) : ViewModel() {

    fun checkDarkModeStatus(): LiveData<Boolean> {
        return settingPrefs.getThemeSetting().asLiveData()
    }

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingPrefs.saveThemeSetting(isDarkModeActive)
        }
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