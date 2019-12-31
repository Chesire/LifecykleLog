package com.chesire.lifecyklelog;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Handles logging out lifecycle methods.
 */
public interface LogHandler {
    /**
     * Executed when a lifecycle method requires logging.
     *
     * @param clazz          string representation of the class that the lifecycle event occurred on.
     * @param lifecycleEvent string representation of the lifecycle event that occurred.
     * @param bundle         the bundle passed into the lifecycle event, or null if not available.
     */
    void logLifecycleMethod(String clazz, String lifecycleEvent, @Nullable Bundle bundle);
}
