package com.chesire.lifecyklelog.events

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.chesire.lifecyklelog.LifecycleEvent
import com.chesire.lifecyklelog.LifecykleLog

/**
 * Handles callbacks for all of the [Activity] lifecycle events.
 * When `onActivityCreated` is called, it will register for the fragment lifecycle events.
 */
internal val activityCallbacks = object : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_CREATE)
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                fragmentCallbacks,
                true
            )
        }
    }

    override fun onActivityStarted(activity: Activity) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_START)
    }

    override fun onActivityResumed(activity: Activity) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_RESUME)
    }

    override fun onActivityPaused(activity: Activity) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_PAUSE)
    }

    override fun onActivityStopped(activity: Activity) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_STOP)
    }

    override fun onActivityDestroyed(activity: Activity) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_DESTROY)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        LifecykleLog.logLifecycle(activity, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
    }
}
