package io.github.shvmsaini.nextdns

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dagger.hilt.android.AndroidEntryPoint
import io.github.shvmsaini.nextdns.utils.ThemeHelper
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val themeHelper = ThemeHelper(this)
        val currTheme = runBlocking {
            themeHelper.getTheme()
        }
        enableEdgeToEdge()
        setContent {
            val theme by themeHelper.themePreferenceFlow.collectAsState(initial = currTheme)
            Log.d(TAG, "onCreate: $theme")
            NavCompose(theme)
        }
    }
}