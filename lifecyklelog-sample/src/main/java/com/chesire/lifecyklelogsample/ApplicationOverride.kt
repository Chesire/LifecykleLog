package com.chesire.lifecyklelogsample

import android.app.Application
import android.util.Log
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog

/**
 * [Application] to show how to use [LifecykleLog].
 */
@Suppress("unused")
class ApplicationOverride : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecykleLog.initialize(
            this,
            defaultLifecycleEvents = arrayOf(
                LifecycleEvent.ON_CREATE,
                LifecycleEvent.ON_ATTACH,
                LifecycleEvent.ON_DESTROY
            )
        ) { clazz, lifecycleEvent ->
            Log.i(clazz, lifecycleEvent)
        }
    }
}
