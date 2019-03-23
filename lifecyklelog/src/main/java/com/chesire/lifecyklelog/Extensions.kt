package com.chesire.lifecyklelog

import android.app.Activity
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.annotations.LogLifecykle

private val annotationClass = LogLifecykle::class.java

/**
 * Gets the [LogLifecykle] annotation if it exists, or null if it doesn't.
 */
internal val Activity.lifecykleLog: LogLifecykle?
    get() = this::class.java.getAnnotation(annotationClass)

/**
 * Gets the [LogLifecykle] annotation if it exists, or null if it doesn't.
 */
internal val Fragment.lifecykleLog: LogLifecykle?
    get() = this::class.java.getAnnotation(annotationClass)

