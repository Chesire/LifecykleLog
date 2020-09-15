package com.chesire.lifecyklelog

import android.os.Bundle

/**
 * Handles logging out lifecycle methods.
 */
fun interface LogHandler {
    /**
     * Executed when a lifecycle method requires logging.
     *
     * [clazz] is the string representation of the class that the lifecycle event occurred on.
     * [lifecycleEvent] is the string representation of the lifecycle event that occurred.
     * [bundle] the bundle passed into the lifecycle event, or null if not available.
     */
    fun logLifecycleMethod(clazz: String, lifecycleEvent: String, bundle: Bundle?)
}
