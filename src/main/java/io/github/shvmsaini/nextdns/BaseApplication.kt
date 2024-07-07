package io.github.shvmsaini.nextdns

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    lateinit var mainActivity: MainActivity
}