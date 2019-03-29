package com.chesire.lifecyklelogsample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.chesire.lifecyklelog.LifecycleEvent;
import com.chesire.lifecyklelog.LifecykleLog;
import com.chesire.lifecyklelog.LogHandler;

/**
 * {@link Application} class to show how to use {@link LifecykleLog} in Java.
 */
@SuppressLint("Registered")
public class JApplicationOverride extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LifecycleEvent[] events = {
                LifecycleEvent.ON_CREATE,
                LifecycleEvent.ON_ATTACH,
                LifecycleEvent.ON_DESTROY
        };
        LifecykleLog.INSTANCE.initialize(this);
        LifecykleLog.INSTANCE.setLogEvents(events);
        LifecykleLog.INSTANCE.setLogHandler(new LogHandler() {
            @Override
            public void logLifecycleMethod(String clazz, String lifecycleEvent) {
                Log.i(clazz, lifecycleEvent);
            }
        });
    }
}
