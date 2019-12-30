package com.chesire.lifecyklelogsample

import android.app.Application
import android.util.Log
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog
import com.chesire.lifecyklelog.LogHandler

/**
 * [Application] to show how to use [LifecykleLog].
 */
@Suppress("unused")
class ApplicationOverride : Application() {
    override fun onCreate() {
        super.onCreate()

        LifecykleLog.apply {
            initialize(this@ApplicationOverride)
            logEvents = arrayOf(
                LifecycleEvent.ON_CREATE,
                LifecycleEvent.ON_ATTACH,
                LifecycleEvent.ON_DESTROY
            )
            logHandler = LogHandler { clazz, lifecycleEvent, bundle ->
                Log.i(clazz, "$lifecycleEvent ${bundle?.let { "- $it" } ?: ""}")
            }
        }
    }
}
