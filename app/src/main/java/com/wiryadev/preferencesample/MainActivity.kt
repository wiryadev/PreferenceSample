package com.wiryadev.preferencesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.wiryadev.preferencesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var settingPrefs: SettingPreferences
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingPrefs = SettingPreferences(this)
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(settingPrefs)
        )[MainViewModel::class.java]

        observeThemeSetting()

        binding.swTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveThemeSettings(isChecked)
            viewModel.checkDarkModeStatus()
        }
    }

    private fun observeThemeSetting() {
        viewModel.isDarkModeActive.observe(this) { isDarkModeActive ->
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