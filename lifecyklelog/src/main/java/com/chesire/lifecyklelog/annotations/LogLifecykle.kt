package com.chesire.lifecyklelog.annotations

import com.chesire.lifecyklelog.flags.FragmentLifecycle

@MustBeDocumented
@Retention
@Target(AnnotationTarget.CLASS)
annotation class LogLifecykle(
    val logStatement: String = "",
    val lifecycleMethods: Array<FragmentLifecycle> = []
)
