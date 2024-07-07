package io.github.shvmsaini.nextdns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import io.github.shvmsaini.nextdns.ui.Settings
import io.github.shvmsaini.nextdns.ui.theme.NextDNSTheme
import io.github.shvmsaini.nextdns.utils.ThemeHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val themeHelper = ThemeHelper(this)
        val currTheme = runBlocking {
            themeHelper.getTheme()
        }
        setContent {
            val theme by themeHelper.themePreferenceFlow.collectAsState(initial = currTheme)
            NextDNSTheme(
                theme = theme,
            ) {
                Settings(theme,
                    onNavigateClick = {
                        onBackPressedDispatcher.onBackPressed()
                    }, onThemeSelected = {
                        lifecycleScope.launch {
                            themeHelper.setTheme(it)
                        }
                    })
            }
        }
    }
}