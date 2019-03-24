package com.chesire.lifecyklelogsample

import android.app.Application
import com.chesire.lifecyklelog.LifecykleLog

/**
 * [Application] to show how to use [LifecykleLog].
 */
@Suppress("unused")
class ApplicationOverride : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecykleLog.initialize(this)
    }
}
