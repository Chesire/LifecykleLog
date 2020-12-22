package com.chesire.lifecyklelogsample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chesire.lifecyklelog.LifecycleEvent;
import com.chesire.lifecyklelog.LifecykleLog;
import com.chesire.lifecyklelog.LogHandler;

import org.jetbrains.annotations.NotNull;

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
            public void logLifecycleMethod(@NotNull String clazz, @NotNull String lifecycleEvent, @Nullable Bundle bundle) {
                Log.i(clazz, lifecycleEvent);
            }
        });
    }
}
