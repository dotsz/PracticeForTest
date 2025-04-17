package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.UserPreferences
import com.example.myapplication.datastore.userPreferencesDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppSettingsViewModel(context: Context) : ViewModel() {

    private val dataStore = context.userPreferencesDataStore

    // Observe darkTheme setting as StateFlow
    val isDarkTheme = dataStore.data
        .map { it.darkTheme }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Toggle dark theme and save to DataStore
    fun toggleDarkTheme() {
        viewModelScope.launch {
            dataStore.updateData { prefs ->
                prefs.toBuilder().setDarkTheme(!prefs.darkTheme).build()
            }
        }
    }
}
