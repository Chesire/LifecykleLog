package com.chesire.lifecyklelog.annotations

@MustBeDocumented
@Retention
@Target(AnnotationTarget.CLASS)
annotation class LogLifecykle(val logStatement: String = "")
