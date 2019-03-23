package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.chesire.lifecyklelog.flags.FragmentLifecycle

object LifecykleLog {
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
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            logLifecycle(f, FragmentLifecycle.ON_ATTACH)
            super.onFragmentAttached(fm, f, context)
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, FragmentLifecycle.ON_CREATE)
            super.onFragmentCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, FragmentLifecycle.ON_CREATE_VIEW)
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        }

        override fun onFragmentActivityCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, FragmentLifecycle.ON_ACTIVITY_CREATED)
            super.onFragmentActivityCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_START)
            super.onFragmentStarted(fm, f)
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_RESUME)
            super.onFragmentResumed(fm, f)
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_PAUSE)
            super.onFragmentPaused(fm, f)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_STOP)
            super.onFragmentStopped(fm, f)
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_DESTROY_VIEW)
            super.onFragmentViewDestroyed(fm, f)
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_DESTROY)
            super.onFragmentDestroyed(fm, f)
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, FragmentLifecycle.ON_DETACH)
            super.onFragmentDetached(fm, f)
        }

        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            logLifecycle(f, FragmentLifecycle.ON_PRE_ATTACHED)
            super.onFragmentPreAttached(fm, f, context)
        }

        override fun onFragmentPreCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, FragmentLifecycle.ON_PRE_CREATED)
            super.onFragmentPreCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentSaveInstanceState(
            fm: FragmentManager,
            f: Fragment,
            outState: Bundle
        ) {
            logLifecycle(f, FragmentLifecycle.ON_SAVE_INSTANCE_STATE)
            super.onFragmentSaveInstanceState(fm, f, outState)
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
}
