package io.github.shvmsaini.nextdns.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ThemeHelper(private val context: Context) {
    companion object {
        private val Context.dataStore by preferencesDataStore("theme_prefs")
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    val themePreferenceFlow: Flow<Theme> = context.dataStore.data.map { preferences ->
        when (preferences[THEME_KEY] ?: "system") {
            "light" -> Theme.LIGHT
            "dark" -> Theme.DARK
            else -> Theme.SYSTEM
        }
    }

    suspend fun getTheme(): Theme {
        return when (context.dataStore.data.first()[THEME_KEY] ?: "system") {
            "light" -> Theme.LIGHT
            "dark" -> Theme.DARK
            else -> Theme.SYSTEM
        }
    }

    suspend fun setTheme(theme: Theme) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = when (theme) {
                Theme.LIGHT -> "light"
                Theme.DARK -> "dark"
                Theme.SYSTEM -> "system"
            }
        }
    }
}

enum class Theme {
    LIGHT, DARK, SYSTEM
}
