package com.chesire.lifecyklelog;

/**
 * Handles logging out lifecycle methods.
 */
public interface LogHandler {
    /**
     * Executed when a lifecycle method requires logging.
     *
     * @param clazz          string representation of the class that the lifecycle event occurred on.
     * @param lifecycleEvent string representation of the lifecycle event that occurred.
     */
    void logLifecycleMethod(String clazz, String lifecycleEvent);
}
