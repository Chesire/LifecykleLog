package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.annotations.LogLifecykle

object LifecykleLog {
    private val annotationClass = LogLifecykle::class.java
    private var log: ((String) -> Unit)? = null

    fun initialize(app: Application, logExecution: ((String) -> Unit)? = null) {
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
            logLifecycle(f, "onCreate")
            super.onFragmentCreated(fm, f, savedInstanceState)
        }
    }

    private fun logLifecycle(fragment: Fragment, lifecycleEvent: String) {
        fragment.lifecykleLog?.let {
            val statement = if (it.logStatement.isNotEmpty()) {
                it.logStatement
            } else {
                fragment::class.java.simpleName
            }
            logLifecycleEvent(statement, lifecycleEvent)
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

    private fun logLifecycleEvent(statement: String, lifecycleEvent: String) {
        val logLine = "$statement ⇀ $lifecycleEvent"
        log?.invoke("$statement ⇀ $lifecycleEvent") ?: Log.d("Lifecykle", logLine)
    }

    private val Activity.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
    private val Fragment.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
}
