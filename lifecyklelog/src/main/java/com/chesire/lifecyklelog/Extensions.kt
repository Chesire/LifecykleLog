package com.chesire.lifecyklelog

import android.app.Activity
import androidx.fragment.app.Fragment
import com.chesire.lifecyklelog.annotations.LogLifecykle

private val annotationClass = LogLifecykle::class.java

internal val Activity.lifecykleLog: LogLifecykle?
    get() = this::class.java.getAnnotation(annotationClass)

internal val Fragment.lifecykleLog: LogLifecykle?
    get() = this::class.java.getAnnotation(annotationClass)

