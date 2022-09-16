package com.wiryadev.preferencesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.wiryadev.preferencesample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var settingPrefs: SettingPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingPrefs = SettingPreferences.getInstance(dataStore)

        checkDarkModeStatus()

        binding.swTheme.setOnCheckedChangeListener { _, isChecked ->
            saveThemeSettings(isChecked)
        }
    }

    private fun checkDarkModeStatus() {
        lifecycleScope.launch {
            settingPrefs.getThemeSetting()
                .flowWithLifecycle(lifecycle)
                .collect { isDarkModeActive ->
                    if (isDarkModeActive) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        binding.swTheme.isChecked = true
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        binding.swTheme.isChecked = false
                    }
                }
        }
    }

    private fun saveThemeSettings(isDarkModeActive: Boolean) {
        lifecycleScope.launch {
            settingPrefs.saveThemeSetting(isDarkModeActive)
        }
    }

}