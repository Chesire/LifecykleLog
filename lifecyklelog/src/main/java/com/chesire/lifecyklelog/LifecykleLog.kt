package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.annotations.LogLifecykle
import com.chesire.lifecyklelog.flags.FragmentLifecycle

object LifecykleLog {
    private val annotationClass = LogLifecykle::class.java
    private lateinit var defaultFragmentMethods: Array<FragmentLifecycle>
    private var log: ((String) -> Unit)? = null

    fun initialize(
        app: Application,
        defaultFragmentLifecycleMethods: Array<FragmentLifecycle> = arrayOf(
            FragmentLifecycle.ON_ATTACH,
            FragmentLifecycle.ON_CREATE,
            FragmentLifecycle.ON_CREATE_VIEW,
            FragmentLifecycle.ON_ACTIVITY_CREATED,
            FragmentLifecycle.ON_START,
            FragmentLifecycle.ON_RESUME,
            FragmentLifecycle.ON_PAUSE,
            FragmentLifecycle.ON_STOP,
            FragmentLifecycle.ON_DESTROY_VIEW,
            FragmentLifecycle.ON_DESTROY,
            FragmentLifecycle.ON_DETACH
        ),
        logExecution: ((String) -> Unit)? = null
    ) {
        defaultFragmentMethods = defaultFragmentLifecycleMethods
        log = logExecution
        setupActivity(app)
    }

    private fun setupActivity(app: Application) =
        app.registerActivityLifecycleCallbacks(activityCallbacks)

    private fun setupFragment(activity: FragmentActivity) =
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, true)

    private val activityCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityStarted(activity: Activity) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            LifecykleLog.logLifecycle(activity, "onCreate")
            if (activity is FragmentActivity) {
                setupFragment(activity)
            }
        }
    }

    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, FragmentLifecycle.ON_CREATE)
            super.onFragmentCreated(fm, f, savedInstanceState)
        }
    }

    private fun logLifecycle(activity: Activity, lifecycleEvent: String) {
        activity.lifecykleLog?.let {
            val statement = if (it.logStatement.isNotEmpty()) {
                it.logStatement
            } else {
                activity::class.java.simpleName
            }
            logLifecycleEvent(statement, lifecycleEvent)
        }
    }

    private fun logLifecycle(fragment: Fragment, lifecycleEvent: FragmentLifecycle) {
        fragment.lifecykleLog?.let { annotation ->
            // Has annotation, perform logging
            if (annotation.lifecycleMethods.isNotEmpty()) {
                // Overridden the defaults, check if should perform logging
                if (!annotation.lifecycleMethods.contains(lifecycleEvent)) {
                    // Don't perform logging as its not overridden
                    return
                }
            } else {
                // Check the defaults
                if (!defaultFragmentMethods.contains(lifecycleEvent)) {
                    // Defaults doesn't contain this event
                    return
                }
            }

            val statement = if (annotation.logStatement.isNotEmpty()) {
                annotation.logStatement
            } else {
                fragment::class.java.simpleName
            }
            logLifecycleEvent(statement, lifecycleEvent.eventName)
        }
    }

    private fun logLifecycleEvent(statement: String, lifecycleEvent: String) {
        val logLine = "$statement ⇀ $lifecycleEvent"
        log?.invoke("$statement ⇀ $lifecycleEvent") ?: Log.d("Lifecykle", logLine)
    }

    private val Activity.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
    private val Fragment.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
}
