package com.chesire.lifecyklelog

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.LifecykleLog.initialize
import com.chesire.lifecyklelog.events.ActivityEvents

/**
 * A container to execute logging on Android lifecycle events.
 * To begin using this call [initialize] passing in the application class.
 */
object LifecykleLog {
    private val annotationClass = LogLifecykle::class.java

    /**
     * An array of lifecycle events to provide logging for.
     * If not overridden then will use the defaults of [LifecycleEvent.ON_ATTACH],
     * [LifecycleEvent.ON_CREATE], [LifecycleEvent.ON_CREATE_VIEW],
     * [LifecycleEvent.ON_ACTIVITY_CREATED], [LifecycleEvent.ON_START],
     * [LifecycleEvent.ON_RESUME], [LifecycleEvent.ON_PAUSE], [LifecycleEvent.ON_STOP],
     * [LifecycleEvent.ON_DESTROY_VIEW], [LifecycleEvent.ON_DESTROY], & [LifecycleEvent.ON_DETACH].
     */
    var logEvents: Array<LifecycleEvent> = arrayOf(
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
    )

    /**
     * Callback to execute when a lifecycle is logged, override this to change how logging occurs.
     * If this is null then it will default to logging using `Log.d`.
     */
    var logHandler: LogHandler? = null

    /**
     * Sets a flag indicating if the annotation is required on an [Activity] or [Fragment] to log
     * the lifecycle events for them. Setting this to false will log out every lifecycle event in
     * [logEvents] for every [Activity] and [Fragment] in the application.
     * If not overridden then will use the default of true.
     */
    var requireAnnotation: Boolean = true

    /**
     * Initializes the [LifecykleLog] with the given [app].
     * Using [app] it will hook into all [Activity] life cycles, and from there the [Fragment]
     * life cycles.
     */
    fun initialize(app: Application) = app.registerActivityLifecycleCallbacks(ActivityEvents)

    /**
     * Checks if the [lifecycleEvent] should be logged, and if it should it will send it through the
     * provided [logHandler].
     */
    internal fun <T : Any> logLifecycle(
        clazz: T,
        lifecycleEvent: LifecycleEvent,
        bundle: Bundle? = null
    ) {
        val annotation = clazz::class.java.getAnnotation(annotationClass)
        val shouldExecuteLog = if (!requireAnnotation) {
            shouldLog(lifecycleEvent, emptyArray())
        } else if (annotation != null) {
            shouldLog(lifecycleEvent, annotation.overrideLogEvents)
        } else {
            false
        }

        if (shouldExecuteLog) {
            executeLog(
                getLogStatement(clazz, annotation),
                lifecycleEvent.eventName,
                bundle
            )
        }
    }

    private fun <T : Any> getLogStatement(clazz: T, annotation: LogLifecykle?): String {
        return if (annotation == null || annotation.className.isEmpty()) {
            clazz::class.java.simpleName
        } else {
            annotation.className
        }
    }

    @Suppress("ReturnCount")
    private fun shouldLog(
        lifecycleEvent: LifecycleEvent,
        overrideEvents: Array<LifecycleEvent>
    ): Boolean {
        if (overrideEvents.isNotEmpty()) {
            // Overridden the defaults, check if should perform logging
            if (!overrideEvents.contains(lifecycleEvent)) {
                // Don't perform logging as this lifecycle event is not wanted
                return false
            }
        } else {
            // Check the defaults
            if (!logEvents.contains(lifecycleEvent)) {
                // Defaults doesn't contain this event
                return false
            }
        }

        return true
    }

    private fun executeLog(statement: String, lifecycleEvent: String, bundle: Bundle?) {
        logHandler
            ?.logLifecycleMethod(statement, lifecycleEvent, bundle)
            ?: Log.d("Lifecykle", "$statement ⇀ $lifecycleEvent")
    }
}
