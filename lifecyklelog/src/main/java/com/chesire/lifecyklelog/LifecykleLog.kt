package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.annotations.LogLifecykle

object LifecykleLog {
    private val annotationClass = LogLifecykle::class.java
    private lateinit var log: (String) -> Unit

    fun initialize(app: Application, logExecution: (String) -> Unit) {
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
            if (activity is FragmentActivity) {
                setupFragment(activity)
            }
            logLifecycleEvent(activity.lifecykleLog, "onCreated")
        }
    }

    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycleEvent(f.lifecykleLog, "onCreate")
            super.onFragmentCreated(fm, f, savedInstanceState)
        }
    }

    private fun logLifecycleEvent(annotation: LogLifecykle?, lifecycleEvent: String) {
        if (annotation == null) {
            return
        }

        val output = if (annotation.logStatement.isNotEmpty()) {
            annotation.logStatement
        } else {
            // use reflection to get the class?
            ""
        }

        // allow this to be nullable - if it is use Log.d here?
        log("$output ⇀ $lifecycleEvent")
    }

    private val Activity.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
    private val Fragment.lifecykleLog: LogLifecykle?
        get() = this::class.java.getAnnotation(annotationClass)
}
