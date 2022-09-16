package com.wiryadev.preferencesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.wiryadev.preferencesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var settingPrefs: SettingPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingPrefs = SettingPreferences(this)

        checkDarkModeStatus()

        binding.swTheme.setOnCheckedChangeListener { _, isChecked ->
            settingPrefs.saveThemeSetting(isChecked)
            checkDarkModeStatus()
        }
    }

    private fun checkDarkModeStatus() {
        val isDarkModeActive = settingPrefs.getThemeSetting()
        if (isDarkModeActive) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.swTheme.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.swTheme.isChecked = false
        }
    }

}