package com.chesire.lifecyklelogsample

import android.app.Application
import android.util.Log
import com.chesire.lifecyklelog.LifecykleLog

@Suppress("unused")
class ApplicationOverride : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecykleLog.initialize(this) {
            Log.d("Lifecykle", it)
        }
    }
}
