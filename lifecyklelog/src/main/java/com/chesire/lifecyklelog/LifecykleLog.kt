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
import com.chesire.lifecyklelog.LifecykleLog.initialize

/**
 * A container to execute logging on Android lifecycle events.
 * To begin using this call [initialize] passing in the application class.
 */
object LifecykleLog {
    private val annotationClass = LogLifecykle::class.java
    private lateinit var logLifecycleEvents: Array<LifecycleEvent>
    private var log: ((String) -> Unit)? = null

    /**
     * Initializes the [LifecykleLog] with the given [app].
     * Using [app] it will hook into all [Activity] life cycles, and from there the [Fragment]
     * life cycles.
     *
     * @param defaultLifecycleEvents An array of lifecycle events to provide logging for, if none
     * are passed in some defaults are used.
     * @param logExecution method to execute when a lifecycle is logged, using this a different log
     * can be used. If nothing is passed in than `Log.d` will be used instead.
     */
    fun initialize(
        app: Application,
        defaultLifecycleEvents: Array<LifecycleEvent> = arrayOf(
            LifecycleEvent.ON_ATTACH,
            LifecycleEvent.ON_CREATE,
            LifecycleEvent.ON_CREATE_VIEW,
            LifecycleEvent.ON_ACTIVITY_CREATED,
            LifecycleEvent.ON_START,
            LifecycleEvent.ON_RESUME,
            LifecycleEvent.ON_PAUSE,
            LifecycleEvent.ON_STOP,
            LifecycleEvent.ON_DESTROY_VIEW,
            LifecycleEvent.ON_DESTROY,
            LifecycleEvent.ON_DETACH
        ),
        logExecution: ((String) -> Unit)? = null
    ) {
        logLifecycleEvents = defaultLifecycleEvents
        log = logExecution
        setupActivity(app)
    }

    private fun setupActivity(app: Application) =
        app.registerActivityLifecycleCallbacks(activityCallbacks)

    private fun setupFragment(activity: FragmentActivity) =
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, true)

    private val activityCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            logLifecycle(activity, LifecycleEvent.ON_CREATE)
            if (activity is FragmentActivity) {
                setupFragment(activity)
            }
        }

        override fun onActivityStarted(activity: Activity) {
            logLifecycle(activity, LifecycleEvent.ON_START)
        }

        override fun onActivityResumed(activity: Activity) {
            logLifecycle(activity, LifecycleEvent.ON_RESUME)
        }

        override fun onActivityPaused(activity: Activity) {
            logLifecycle(activity, LifecycleEvent.ON_PAUSE)
        }

        override fun onActivityStopped(activity: Activity) {
            logLifecycle(activity, LifecycleEvent.ON_STOP)
        }

        override fun onActivityDestroyed(activity: Activity) {
            logLifecycle(activity, LifecycleEvent.ON_DESTROY)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
            logLifecycle(activity, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
        }
    }

    @Suppress("TooManyFunctions")
    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            logLifecycle(f, LifecycleEvent.ON_ATTACH)
            super.onFragmentAttached(fm, f, context)
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, LifecycleEvent.ON_CREATE)
            super.onFragmentCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, LifecycleEvent.ON_CREATE_VIEW)
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        }

        override fun onFragmentActivityCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, LifecycleEvent.ON_ACTIVITY_CREATED)
            super.onFragmentActivityCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_START)
            super.onFragmentStarted(fm, f)
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_RESUME)
            super.onFragmentResumed(fm, f)
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_PAUSE)
            super.onFragmentPaused(fm, f)
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_STOP)
            super.onFragmentStopped(fm, f)
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_DESTROY_VIEW)
            super.onFragmentViewDestroyed(fm, f)
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_DESTROY)
            super.onFragmentDestroyed(fm, f)
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            logLifecycle(f, LifecycleEvent.ON_DETACH)
            super.onFragmentDetached(fm, f)
        }

        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            logLifecycle(f, LifecycleEvent.ON_PRE_ATTACHED)
            super.onFragmentPreAttached(fm, f, context)
        }

        override fun onFragmentPreCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            logLifecycle(f, LifecycleEvent.ON_PRE_CREATED)
            super.onFragmentPreCreated(fm, f, savedInstanceState)
        }

        override fun onFragmentSaveInstanceState(
            fm: FragmentManager,
            f: Fragment,
            outState: Bundle
        ) {
            logLifecycle(f, LifecycleEvent.ON_SAVE_INSTANCE_STATE)
            super.onFragmentSaveInstanceState(fm, f, outState)
        }
    }

    private fun <T : Any> logLifecycle(clazz: T, lifecycleEvent: LifecycleEvent) {
        clazz::class.java.getAnnotation(annotationClass)?.let { annotation ->
            if (annotation.overrideLifecycleEvents.isNotEmpty()) {
                // Overridden the defaults, check if should perform logging
                if (!annotation.overrideLifecycleEvents.contains(lifecycleEvent)) {
                    // Don't perform logging as this lifecycle event is not wanted
                    return
                }
            } else {
                // Check the defaults
                if (!logLifecycleEvents.contains(lifecycleEvent)) {
                    // Defaults doesn't contain this event
                    return
                }
            }

            val statement = if (annotation.className.isNotEmpty()) {
                annotation.className
            } else {
                clazz::class.java.simpleName
            }
            executeLog(statement, lifecycleEvent.eventName)
        }
    }

    private fun executeLog(statement: String, lifecycleEvent: String) {
        val logLine = "$statement ⇀ $lifecycleEvent"
        log?.invoke(logLine) ?: Log.d("Lifecykle", logLine)
    }
}
