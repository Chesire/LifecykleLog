package com.chesire.lifecyklelog

import android.app.Activity
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.LifecykleLog
import com.chesire.lifecyklelog.LifecycleEvent

/**
 * Annotation to put on an [Activity] or a [Fragment] that denotes its lifecycle methods should be
 * logged out.
 * If the [className] is provided, this is the name that will be used for this class,
 * use either the name of the class or something descriptive to see in the logs. If nothing is
 * provided then [LifecykleLog] will attempt to get it by inspecting the class.
 * By default all lifecycle methods that were initialized in [LifecykleLog] will be logged out,
 * if the [overrideLifecycleEvents] is provided then only the methods provided in there will be
 * logged out.
 */
@MustBeDocumented
@Retention
@Target(AnnotationTarget.CLASS)
annotation class LogLifecykle(
    val className: String = "",
    val overrideLifecycleEvents: Array<LifecycleEvent> = []
)
